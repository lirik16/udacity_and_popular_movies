package com.and.movies.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.and.movies.databinding.MovieListItemBinding;
import com.and.movies.domain.repo.movie.MovieInfo;

import java.util.Collections;
import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder>
        implements DataSetChanger<List<MovieInfo>> {

    @NonNull
    private List<MovieInfo> mMovieInfoList;

    public MovieListAdapter() {
        mMovieInfoList = Collections.emptyList();
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
    public void changeDataSet(@NonNull final List<MovieInfo> dataSet) {
        //TODO: Add DiffUtil
        if (dataSet != null) {
            mMovieInfoList = dataSet;
        } else {
            mMovieInfoList = Collections.emptyList();
        }

        notifyDataSetChanged();
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
