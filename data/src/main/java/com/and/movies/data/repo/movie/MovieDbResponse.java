package com.and.movies.data.repo.movie;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieDbResponse<ResultType> {
    @Nullable
    @SerializedName("results")
    private final List<ResultType> mResult;

    public MovieDbResponse(@Nullable final List<ResultType> result) {
        mResult = result;
    }

    @Nullable
    public List<ResultType> getResult() {
        return mResult;
    }
}
