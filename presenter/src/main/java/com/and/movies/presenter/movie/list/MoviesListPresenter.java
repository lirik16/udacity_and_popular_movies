package com.and.movies.presenter.movie.list;

import android.support.annotation.NonNull;
import android.support.annotation.UiThread;

import com.and.movies.domain.repo.movie.MovieInfo;
import com.and.movies.domain.repo.movie.MoviesSortOrder;
import com.and.movies.domain.usecase.GetMovieListUseCase;
import com.and.movies.domain.usecase.resource.Resource;
import com.and.movies.domain.usecase.resource.ResourceStatus;
import com.and.movies.domain.usecase.resource.ResponseListener;
import com.and.movies.presenter.base.BasePresenter;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import dagger.Lazy;

public class MoviesListPresenter extends BasePresenter<MoviesListView> {
    @NonNull
    private final GetMovieListUseCase mGetMovieListUseCase;
    @NonNull
    //Lazy<ViewModel> is a workaround because https://github.com/google/dagger/issues/1107
    private final Lazy<MoviesListViewState> mMoviesListViewState;

    @Inject
    public MoviesListPresenter(@NonNull final MoviesListView moviesListView,
                               @NonNull final GetMovieListUseCase getMovieListUseCase,
                               @NonNull final Lazy<MoviesListViewState> moviesListViewState) {
        super(moviesListView);
        mGetMovieListUseCase = getMovieListUseCase;
        mMoviesListViewState = moviesListViewState;
    }

    private MoviesListViewState viewState() {
        return mMoviesListViewState.get();
    }

    @Override
    public void init() {
        super.init();

        performViewAction(view -> view.setViewState(viewState()));
    }

    @UiThread
    public void loadMoviesList(final boolean isInitialLoad) {
        if (isInitialLoad && !viewState().getMovieInfoList().isEmpty()) {
            return;
        }

        final int sortOrder = viewState().getMoviesSortOrder().get();
        final GetMovieListUseCase.Request request = GetMovieListUseCase.Request.create(sortOrder);

        //TODO: replace custom job engine to something more stable
        if (viewState().getGetMovieListListener() != null) {
            viewState().getGetMovieListListener().cancel();
        }
        viewState().setGetMovieListListener(new ResponseListener<List<? extends MovieInfo>>() {
            @Override
            @UiThread
            public void onResponse(@NonNull final Resource<List<? extends MovieInfo>> resource) {
                switch (resource.getStatus()) {
                    case ResourceStatus.FAILED:
                        viewState().setIsLoadingMovieInfoList(false);
                        performViewAction(view -> {
                            view.showErrorMessage("Error message");
                        });
                        break;
                    case ResourceStatus.IN_PROGRESS:
                        viewState().setIsLoadingMovieInfoList(true);
                        break;
                    case ResourceStatus.SUCCEED:
                        viewState().setIsLoadingMovieInfoList(false);
                        final List<? extends MovieInfo> result = resource.getResult();
                        if (result != null) {
                            viewState().setMovieInfoList(result);
                        } else {
                            viewState().setMovieInfoList(Collections.emptyList());
                        }
                        break;
                }
            }
        });
        mGetMovieListUseCase.execute(request, viewState().getGetMovieListListener());
    }

    @UiThread
    public void setMoviesSortOrder(@MoviesSortOrder final int sortOrder) {
        if (viewState().getMoviesSortOrder().get() != sortOrder) {
            viewState().setMovieSortOrder(sortOrder);
            loadMoviesList(false);
        }
    }
}
