
package es.voghdev.prjdagger2.usecase;

import java.util.List;

import es.voghdev.prjdagger2.global.model.User;

public interface GetUsers {
    List<User> get();

    void getAsync(final Listener listener);

    interface Listener {
        void onUsersReceived(final List<User> users, boolean isCached);

        void onError(Exception e);

        void onNoInternetAvailable();
    }
}
