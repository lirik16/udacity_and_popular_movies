package com.and.movies.di;

import com.and.movies.di.scope.ActivityScope;
import com.and.movies.list.MovieListActivity;
import com.and.movies.list.MovieListModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = MovieListModule.class)
    abstract MovieListActivity movieListActivity();
}
