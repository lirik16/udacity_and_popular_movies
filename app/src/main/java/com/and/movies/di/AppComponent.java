package com.and.movies.di;

import android.app.Application;

import com.and.movies.AppApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AppModule.class,
        ActivityBindingModule.class,
        ThreadsModule.class,
        NetworkModule.class,
        ReposModule.class,
        AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<AppApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}

