package com.and.movies.domain.repo.movie;

import android.net.Uri;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class MovieInfo {
    public static MovieInfo create(String title,
                                   String releaseDate,
                                   Uri posterPath,
                                   Long voteAverage,
                                   String plot) {
        return new AutoValue_MovieInfo(title, releaseDate, posterPath, voteAverage, plot);
    }
    abstract String getTitle();
    abstract String getReleaseDate();
    abstract Uri getPosterPath();
    abstract Long getVoteAverage();
    abstract String getPlot();
}
