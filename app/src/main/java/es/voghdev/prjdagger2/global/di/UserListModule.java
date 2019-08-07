
package es.voghdev.prjdagger2.global.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import es.voghdev.prjdagger2.ui.view.ShowUserClickedToastImpl;
import es.voghdev.prjdagger2.ui.view.ShowUserGreetingToastImpl;
import es.voghdev.prjdagger2.usecase.ShowUserClicked;
import es.voghdev.prjdagger2.usecase.ShowUserGreeting;

@Module
public class UserListModule {

    Context mContext;
    ShowUserClicked showUserClickedToast;
    ShowUserGreeting showUserGreetingToast;

    public UserListModule(final Context context) {
        mContext = context;

        showUserClickedToast = new ShowUserClickedToastImpl(mContext);
        showUserGreetingToast = new ShowUserGreetingToastImpl(mContext);
    }

    @Provides
    public ShowUserClicked provideShowUserClicked() {
        return showUserClickedToast;
    }

    @Provides
    public ShowUserGreeting provideShowUserGreeting() {
        return showUserGreetingToast;
    }
}
