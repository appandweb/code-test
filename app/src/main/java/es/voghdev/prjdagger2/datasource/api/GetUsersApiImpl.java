
package es.voghdev.prjdagger2.datasource.api;

import java.util.ArrayList;
import java.util.List;

import es.voghdev.prjdagger2.datasource.api.model.UserApiEntry;
import es.voghdev.prjdagger2.datasource.api.retrofit.GetUsersRetrofitRequest;
import es.voghdev.prjdagger2.global.model.User;
import es.voghdev.prjdagger2.usecase.GetUsers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetUsersApiImpl implements GetUsers, Callback<GetUsersResponse> {
    private static final String ENDPOINT = "https://api.randomuser.me/";

    private int pageSize;
    private int pageNumber;

    private Listener listener = new NullListener();

    public GetUsersApiImpl(int pageSize, int pageNumber) {
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
    }

    @Override
    public List<User> get() {
        throw new IllegalStateException("Not implemented yet");
    }

    @Override
    public void getAsync(Listener listener) {
        if (listener != null) {
            this.listener = listener;
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetUsersRetrofitRequest request = retrofit.create(GetUsersRetrofitRequest.class);
        Call<GetUsersResponse> call = request.getRandomUsers(pageSize, pageNumber);
        call.enqueue(this);
    }

    @Override

    public void onResponse(Call<GetUsersResponse> call, Response<GetUsersResponse> response) {
        List<User> users = new ArrayList<User>();

        for (UserApiEntry entry : response.body().getResults()) {
            users.add(entry.parseUser());
        }

        listener.onUsersReceived(users, false);
    }

    @Override
    public void onFailure(Call<GetUsersResponse> call, Throwable t) {
        listener.onError(new Exception(t));
    }

    private class NullListener implements Listener {
        public void onUsersReceived(List<User> users, boolean isCached) {
            /* Empty */
        }

        public void onError(Exception e) {
            /* Empty */
        }

        public void onNoInternetAvailable() {
            /* Empty */
        }
    }
}
