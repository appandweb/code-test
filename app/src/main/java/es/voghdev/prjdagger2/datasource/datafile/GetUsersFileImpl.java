/*
 * Copyright (C) 2015 Olmo Gallegos Hernández.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package es.voghdev.prjdagger2.datasource.datafile;

import android.content.Context;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import es.voghdev.prjdagger2.R;
import es.voghdev.prjdagger2.datasource.api.GetUsersResponse;
import es.voghdev.prjdagger2.datasource.api.model.UserApiEntry;
import es.voghdev.prjdagger2.global.model.User;
import es.voghdev.prjdagger2.interactor.Interactor;
import es.voghdev.prjdagger2.interactor.MainThread;
import es.voghdev.prjdagger2.interactor.impl.ThreadExecutor;
import es.voghdev.prjdagger2.usecase.GetUsers;

public class GetUsersFileImpl implements Interactor, GetUsers {
    private Context mContext;
    private ThreadExecutor threadExecutor;
    private MainThread mainThread;
    private Listener listener = new EmptyListener();

    public GetUsersFileImpl(Context applicationContext, ThreadExecutor threadExecutor, MainThread mainThread) {
        this.mContext = applicationContext;
        this.threadExecutor = threadExecutor;
        this.mainThread = mainThread;
    }

    private List<User> parseUsersFromRawFile(int resId) throws IOException {
        InputStream inputStream = mContext.getResources().openRawResource(resId);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        int x;
        byte[] buffer = new byte[1024];
        while ((x = inputStream.read(buffer, 0, buffer.length)) != -1) {
            bos.write(buffer, 0, x);
        }
        inputStream.close();

        String json = bos.toString();
        return getUsersFromJson(json);

    }

    private List<User> getUsersFromJson(String json) {
        GetUsersResponse response = new Gson().fromJson(json, GetUsersResponse.class);
        List<User> users = new ArrayList<>();
        for (UserApiEntry entry : response.getResults()) {
            users.add(entry.parseUser());
        }
        return users;
    }

    @Override
    public List<User> get() {
        try {
            return parseUsersFromRawFile(R.raw.users);
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public void getAsync(final Listener listener) {
        this.listener = listener;
        this.threadExecutor.run(this);

    }

    @Override
    public void run() {
        try {
            final List<User> users = parseUsersFromRawFile(R.raw.users);

            wait(3000);

            mainThread.post(new Runnable() {
                @Override
                public void run() {
                    listener.onUsersReceived(users, true);
                }
            });
        } catch (IOException e) {
            listener.onError(e);
        } catch (InterruptedException e) {
            listener.onError(e);
        }
    }

    private class EmptyListener implements Listener {
        @Override
        public void onUsersReceived(List<User> users, boolean isCached) {
            /* Empty */
        }

        @Override
        public void onError(Exception e) {
            /* Empty */
        }

        @Override
        public void onNoInternetAvailable() {
            /* Empty */
        }
    }
}
