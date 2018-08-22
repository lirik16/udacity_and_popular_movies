package com.and.movies.data.repo.movie;

import android.support.annotation.Nullable;

import com.and.movies.domain.repo.movie.MovieInfo;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class GsonMovieInfo implements MovieInfo {

    public static TypeAdapter<GsonMovieInfo> typeAdapter(Gson gson) {
        return new AutoValue_GsonMovieInfo.GsonTypeAdapter(gson);
    }

    @SerializedName("id")
    @Override
    public abstract long getId();

    @SerializedName("title")
    @Nullable
    @Override
    public abstract String getTitle();

    @SerializedName("release_date")
    @Nullable
    @Override
    public abstract String getReleaseDate();

    @SerializedName("poster_path")
    @Nullable
    @Override
    public abstract String getPosterPath();

    @SerializedName("vote_average")
    @Override
    public abstract float getVoteAverage();

    @SerializedName("overview")
    @Nullable
    @Override
    public abstract String getPlot();
}
