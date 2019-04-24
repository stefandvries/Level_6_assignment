package com.example.level_6_assignment;

import android.util.Log;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TMDbApi {

    @GET("discover/movie")
    Call<MoviesResponse> getPopularMovies(
            @Query("api_key") String apiKey,
            @Query("primary_release_year") int ReleaseYear,
            @Query("language") String language,
            @Query("page") int page
    );
}


