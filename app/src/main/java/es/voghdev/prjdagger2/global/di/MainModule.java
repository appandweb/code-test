
package es.voghdev.prjdagger2.global.di;

import android.content.Context;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import es.voghdev.prjdagger2.datasource.api.GetUsersApiImpl;
import es.voghdev.prjdagger2.datasource.datafile.GetUsersFileImpl;
import es.voghdev.prjdagger2.global.App;
import es.voghdev.prjdagger2.interactor.GetUsersInteractor;
import es.voghdev.prjdagger2.interactor.impl.MainThreadImpl;
import es.voghdev.prjdagger2.interactor.impl.ThreadExecutor;
import es.voghdev.prjdagger2.repository.UserRepository;

@Module
public class MainModule {
    private App application;

    GetUsersInteractor interactor;
    UserRepository userRepository;

    public MainModule(App application) {
        this.application = application;

        interactor = new GetUsersInteractor(new GetUsersApiImpl(10, 0),
                new ThreadExecutor(),
                new MainThreadImpl());

        userRepository = new UserRepository(application, interactor, new GetUsersFileImpl(application,
                new ThreadExecutor(),
                new MainThreadImpl()));
    }

    @Provides
    GetUsersInteractor provideUserInteractor() {
        return interactor;
    }

    @Provides
    UserRepository provideUserRepository() {
        return userRepository;
    }

    @Provides
    @Named("applicationContext")
    Context provideApplicationContext() {
        return application.getApplicationContext();
    }

}