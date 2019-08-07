
package es.voghdev.prjdagger2.interactor;

import java.util.List;

import es.voghdev.prjdagger2.global.model.User;
import es.voghdev.prjdagger2.usecase.GetUsers;

public class GetUsersInteractor implements Interactor, GetUsers, GetUsers.Listener {
    GetUsers.Listener listener = new NullListener();
    GetUsers getUsers;
    Executor executor;
    MainThread mainThread;

    public GetUsersInteractor(GetUsers dataSource, Executor executor, MainThread mainThread) {
        this.getUsers = dataSource;
        this.executor = executor;
        this.mainThread = mainThread;
    }

    @Override
    public void onUsersReceived(List<User> list, boolean isCached) {
        listener.onUsersReceived(list, isCached);
    }

    @Override
    public void onError(Exception e) {
        listener.onError(e);
    }

    @Override
    public void onNoInternetAvailable() {
        listener.onNoInternetAvailable();
    }

    @Override
    public void run() {
        getUsers.getAsync(listener);
    }

    @Override
    public List<User> get() {
        throw new IllegalArgumentException("Please use async version of this Interactor");
    }

    @Override
    public void getAsync(Listener listener) {
        if (listener != null) {
            this.listener = listener;
        }
        this.executor.run(this);
    }

    private class NullListener implements GetUsers.Listener {
        public void onUsersReceived(List<User> list, boolean isCached) {
        }

        public void onError(Exception e) {
        }

        public void onNoInternetAvailable() {
        }
    }
}
