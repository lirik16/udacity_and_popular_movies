package com.and.movies.list;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.and.movies.databinding.MovieListItemBinding;
import com.and.movies.domain.repo.movie.MovieInfo;

import java.util.ArrayList;
import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder>
        implements DataSetChanger<List<MovieInfo>> {

    @NonNull
    private List<MovieInfo> mMovieInfoList;

    public MovieListAdapter() {
        mMovieInfoList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        return new ViewHolder(MovieListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.setMovieInfo(mMovieInfoList.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovieInfoList.size();
    }

    @Override
    public void changeDataSet(@Nullable final List<MovieInfo> dataSet) {
        if (dataSet == null || dataSet.isEmpty()) {
            final int oldDataSetSize = mMovieInfoList.size();
            mMovieInfoList.clear();
            notifyItemRangeRemoved(0, oldDataSetSize);
        } else if (mMovieInfoList.isEmpty()) {
            mMovieInfoList.addAll(dataSet);
            notifyItemRangeInserted(0, mMovieInfoList.size());
        } else {
            final MovieListDiffUtil callback = new MovieListDiffUtil(mMovieInfoList, dataSet);
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);
            mMovieInfoList.clear();
            mMovieInfoList.addAll(dataSet);
            result.dispatchUpdatesTo(this);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @NonNull
        private final MovieListItemBinding mMovieListItemBinding;

        ViewHolder(@NonNull final MovieListItemBinding movieListItemBinding) {
            super(movieListItemBinding.getRoot());
            mMovieListItemBinding = movieListItemBinding;
        }

        void setMovieInfo(@NonNull final MovieInfo movieInfo) {
            mMovieListItemBinding.setMovieInfo(movieInfo);
            mMovieListItemBinding.executePendingBindings();
        }
    }
}
