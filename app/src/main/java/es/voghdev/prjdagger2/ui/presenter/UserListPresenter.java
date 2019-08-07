
package es.voghdev.prjdagger2.ui.presenter;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import es.voghdev.prjdagger2.global.App;
import es.voghdev.prjdagger2.global.di.RootComponent;
import es.voghdev.prjdagger2.global.model.User;
import es.voghdev.prjdagger2.interactor.GetUsersInteractor;
import es.voghdev.prjdagger2.usecase.GetUsers;

public class UserListPresenter extends Presenter<UserListPresenter.View> {

    protected GetUsers interactor;

    protected Context context;

    @Inject
    public UserListPresenter(Context ctx, GetUsersInteractor getUsersInteractor) {
        context = ctx;
        interactor = getUsersInteractor;

        getComponent().inject(this);
    }

    @Override
    public void initialize() {
        view.showLoading();
        interactor.getAsync(new GetUsers.Listener() {
            @Override
            public void onUsersReceived(List<User> users, boolean isCached) {
                view.showUserList(users);
                view.hideLoading();
            }

            @Override
            public void onError(Exception e) {
                view.showUserListError(e);
                view.hideLoading();
            }

            @Override
            public void onNoInternetAvailable() {
                view.showNoInternetMessage();
                view.hideLoading();
            }

        });
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    public void onUserPictureClicked(User user) {
        view.makeUserSayHello(user);
    }

    public void onUserRowClicked(User user) {
        view.showUserClickedMessage(user);
    }

    protected RootComponent getComponent() {
        return ((App) context.getApplicationContext()).getComponent();
    }

    public interface View {
        void showUserList(List<User> users);

        void showUserListError(Exception e);

        void showNoInternetMessage();

        void showLoading();

        void hideLoading();

        void makeUserSayHello(User user);

        void showUserClickedMessage(User user);
    }
}
