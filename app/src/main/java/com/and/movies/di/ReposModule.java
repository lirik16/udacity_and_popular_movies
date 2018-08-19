package com.and.movies.di;

import com.and.movies.data.repo.movie.RetrofitMovieRepo;
import com.and.movies.domain.repo.movie.MovieRepo;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ReposModule {
    @Binds
    abstract MovieRepo movieRepo(RetrofitMovieRepo retrofitMovieRepo);
}
