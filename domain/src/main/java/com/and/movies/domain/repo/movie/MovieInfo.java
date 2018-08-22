package com.and.movies.domain.repo.movie;

import android.support.annotation.Nullable;

public interface MovieInfo {
    long getId();

    @Nullable
    String getTitle();

    @Nullable
    String getReleaseDate();

    @Nullable
    String getPosterPath();

    float getVoteAverage();

    @Nullable
    String getPlot();
}
