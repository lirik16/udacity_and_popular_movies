package com.and.movies.data.repo.movie;

import android.support.annotation.NonNull;

import com.and.movies.data.BuildConfig;
import com.and.movies.domain.repo.error.RepoException;
import com.and.movies.domain.repo.movie.MovieInfo;
import com.and.movies.domain.repo.movie.MovieRepo;
import com.and.movies.domain.repo.movie.MoviesSortOrder;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class RetrofitMovieRepo implements MovieRepo {
    @NonNull
    private final MovieDbApi mMovieDbApi;

    public RetrofitMovieRepo(@NonNull final MovieDbApi movieDbApi) {
        mMovieDbApi = movieDbApi;
    }

    @NonNull
    @Override
    public List<? extends MovieInfo> getMovies(@MoviesSortOrder final int sortOrder) {
        final Call<MovieDbResponse<GsonMovieInfo>> call;
        switch (sortOrder) {
            case MoviesSortOrder.HIGHEST_RATED:
                call = mMovieDbApi.getHighestRatedMovies(BuildConfig.THE_MOVIE_DB_API_KEY_V3);
                break;
            case MoviesSortOrder.MOST_POPULAR:
                call = mMovieDbApi.getPopularMovies(BuildConfig.THE_MOVIE_DB_API_KEY_V3);
                break;
            default:
                throw new IllegalArgumentException("Invalid sort order");
        }

        try {
            final Response<MovieDbResponse<GsonMovieInfo>> response = call.execute();
            if (response.isSuccessful()) {
                final MovieDbResponse<GsonMovieInfo> movieDbResponse = response.body();
                if (movieDbResponse != null) {
                    final List<GsonMovieInfo> moviesList = movieDbResponse.getResult();
                    if (moviesList != null) {
                        return moviesList;
                    }
                }
            }

            throw new RepoException("Invalid response");
        } catch (IOException e) {
            throw new RepoException(e);
        }
    }
}
