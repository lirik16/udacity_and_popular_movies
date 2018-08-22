package com.and.movies.domain.repo.movie;

import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;

import java.util.List;

public interface MovieRepo {
    @NonNull
    @WorkerThread
    List<? extends MovieInfo> getMovies(@MoviesSortOrder final int sortOrder);
}
