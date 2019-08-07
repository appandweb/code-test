
package es.voghdev.prjdagger2.global.di;

import dagger.Component;
import es.voghdev.prjdagger2.global.App;
import es.voghdev.prjdagger2.ui.activity.BaseActivity;
import es.voghdev.prjdagger2.ui.presenter.UserListPresenter;

@Component(modules = MainModule.class)
public interface RootComponent {

    void inject(BaseActivity activity); // BaseActivity can inject dependencies from this Component

    void inject(App application);

    void inject(UserListPresenter presenter); // UserListPresenter can inject dependencies from this Component
}