
package es.voghdev.prjdagger2.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.pedrogomez.renderers.ListAdapteeCollection;
import com.pedrogomez.renderers.RVRendererAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;
import es.voghdev.prjdagger2.R;
import es.voghdev.prjdagger2.global.App;
import es.voghdev.prjdagger2.global.di.DaggerUserListComponent;
import es.voghdev.prjdagger2.global.di.UserListComponent;
import es.voghdev.prjdagger2.global.di.UserListModule;
import es.voghdev.prjdagger2.global.model.User;
import es.voghdev.prjdagger2.interactor.GetUsersInteractor;
import es.voghdev.prjdagger2.ui.presenter.UserListPresenter;
import es.voghdev.prjdagger2.ui.renderer.UserRenderer;
import es.voghdev.prjdagger2.ui.renderer.UserRendererBuilder;
import es.voghdev.prjdagger2.usecase.ShowUserClicked;
import es.voghdev.prjdagger2.usecase.ShowUserGreeting;

public class UserListActivity extends BaseActivity implements UserListPresenter.View {
    @InjectView(R.id.users_list)
    RecyclerView recyclerView;

    @InjectView(R.id.users_progressBar)
    ProgressBar progressBar;

    RVRendererAdapter<User> adapter;

    UserListPresenter presenter;

    @Inject
    GetUsersInteractor getUsersInteractor;

    @Inject
    ShowUserClicked showUserClicked;
    @Inject
    ShowUserGreeting showUserGreeting;

    private UserListComponent component;

    final UserRenderer.OnUserClicked mUserClickListener = new UserRenderer.OnUserClicked() {
        @Override
        public void onPictureClicked(User user) {
            presenter.onUserPictureClicked(user);
        }

        @Override
        public void onBackgroundClicked(User user) {
            presenter.onUserRowClicked(user);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        component().inject(this);

        adapter = new RVRendererAdapter<User>(
                LayoutInflater.from(this),
                new UserRendererBuilder(this, mUserClickListener),
                new ListAdapteeCollection<User>(new ArrayList<User>())
        );

        presenter = new UserListPresenter(this, getUsersInteractor);
        presenter.setView(this);
        presenter.initialize();

        initializeRecyclerView();
    }

    private void initializeRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_users_list;
    }

    @Override
    public void showUserList(List<User> users) {
        for (User u : users) {
            adapter.add(u);
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    public void showUserListError(Exception e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNoInternetMessage() {
        Toast.makeText(this, R.string.no_internet, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void makeUserSayHello(User user) {
        showUserGreeting.show(user);
    }

    @Override
    public void showUserClickedMessage(User user) {
        showUserClicked.show(user);
    }

    private UserListComponent component() {
        if (component == null) {
            component = DaggerUserListComponent.builder()
                    .rootComponent(((App) getApplication()).getComponent())
                    .userListModule(new UserListModule(getApplicationContext()))
                    .mainModule(((App) getApplication()).getMainModule())
                    .build();
        }
        return component;
    }
}
