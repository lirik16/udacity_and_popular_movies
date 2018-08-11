package com.and.movies.domain.repo.movie;

import android.support.annotation.Nullable;

public interface MovieInfo {
    @Nullable
    String getTitle();

    @Nullable
    String getReleaseDate();

    @Nullable
    String getPosterPath();

    @Nullable
    Float getVoteAverage();

    @Nullable
    String getPlot();
}
