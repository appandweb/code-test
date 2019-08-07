
package es.voghdev.prjdagger2.global.di;

import dagger.Component;
import es.voghdev.prjdagger2.ui.activity.UserListActivity;

@Component(dependencies = RootComponent.class, modules = {UserListModule.class, MainModule.class})
public interface UserListComponent {
    void inject(UserListActivity activity);
}
