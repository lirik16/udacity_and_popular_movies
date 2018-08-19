package com.and.movies.data.repo.movie;

import android.support.annotation.Nullable;

import com.and.movies.domain.repo.movie.MovieInfo;
import com.google.gson.annotations.SerializedName;

public class GsonMovieInfo implements MovieInfo {
    @Nullable
    @SerializedName("title")
    private final String mTitle;
    @Nullable
    @SerializedName("release_date")
    private final String mReleaseDate;
    @Nullable
    @SerializedName("poster_path")
    private final String mPosterPath;
    @Nullable
    @SerializedName("vote_average")
    private final Float mVoteAverage;
    @Nullable
    @SerializedName("overview")
    private final String mPlot;

    public GsonMovieInfo(@Nullable final String title,
                         @Nullable final String releaseDate,
                         @Nullable final String posterPath,
                         @Nullable final Float voteAverage,
                         @Nullable final String plot) {
        mTitle = title;
        mReleaseDate = releaseDate;
        mPosterPath = posterPath;
        mVoteAverage = voteAverage;
        mPlot = plot;
    }

    @Nullable
    @Override
    public String getTitle() {
        return mTitle;
    }

    @Nullable
    @Override
    public String getReleaseDate() {
        return mReleaseDate;
    }

    @Nullable
    @Override
    public String getPosterPath() {
        return mPosterPath;
    }

    @Nullable
    @Override
    public Float getVoteAverage() {
        return mVoteAverage;
    }

    @Nullable
    @Override
    public String getPlot() {
        return mPlot;
    }
}
