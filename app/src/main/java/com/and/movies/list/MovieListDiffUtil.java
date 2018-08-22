package com.and.movies.list;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import com.and.movies.domain.repo.movie.MovieInfo;

import java.util.List;
import java.util.Objects;

public class MovieListDiffUtil extends DiffUtil.Callback {
    @NonNull
    private final List<MovieInfo> mOldList;
    @NonNull
    private final List<MovieInfo> mNewList;

    public MovieListDiffUtil(@NonNull final List<MovieInfo> oldList,
                             @NonNull final List<MovieInfo> newList) {
        mOldList = oldList;
        mNewList = newList;
    }

    @Override
    public int getOldListSize() {
        return mOldList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldList.get(oldItemPosition).getId() ==
                mNewList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return Objects.equals(mOldList.get(oldItemPosition),
                mNewList.get(newItemPosition));
    }
}
