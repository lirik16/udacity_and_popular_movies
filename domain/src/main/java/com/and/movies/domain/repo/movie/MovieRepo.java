package com.and.movies.domain.repo.movie;

import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;

import java.util.List;

@WorkerThread
public interface MovieRepo {
    @NonNull
    List<MovieInfo> getMovies(@MoviesSortOrder final int sortOrder);
}
