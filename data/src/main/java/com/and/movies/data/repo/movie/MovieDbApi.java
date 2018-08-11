package com.and.movies.data.repo.movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDbApi {
    @GET("/movie/popular")
    Call<MovieDbResponse<GsonMovieInfo>> getPopularMovies(@Query("api_key") String apiKey);

    @GET("/movie/top_rated")
    Call<MovieDbResponse<GsonMovieInfo>> getHighestRatedMovies(@Query("api_key") String apiKey);
}
