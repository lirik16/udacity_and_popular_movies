package com.and.movies.list;

import android.databinding.BindingAdapter;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.and.movies.domain.repo.movie.MovieInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieListBindings {
    @BindingAdapter("items")
    public static void setItems(@NonNull final RecyclerView recyclerView,
                                @NonNull final List<MovieInfo> movieInfoList) {
        final RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter != null) {
            final DataSetChanger<List<MovieInfo>> dataSetChanger =
                    (DataSetChanger<List<MovieInfo>>) adapter;
            dataSetChanger.changeDataSet(movieInfoList);
        }
    }

    @BindingAdapter("imageUriPath")
    public static void setImageUriPath(@NonNull final ImageView imageView,
                                       @NonNull final String imageUriPath) {
        final ViewTreeObserver viewTreeObserver = imageView.getViewTreeObserver();
        viewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {

            @Override
            public boolean onPreDraw() {
                final Uri imageUri = ImageUtil.createImageUriForView(imageView, imageUriPath);
                Picasso.get().load(imageUri).into(imageView);

                imageView.getViewTreeObserver().removeOnPreDrawListener(this);
                return true;
            }
        });
    }

    @BindingAdapter("refreshing")
    public static void setRefreshing(@NonNull final SwipeRefreshLayout swipeRefreshLayout,
                                     final boolean isRefreshing) {
        swipeRefreshLayout.setRefreshing(isRefreshing);

    }
}
