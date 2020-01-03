package com.laylamac.finalprojectmade.api

import com.laylamac.finalprojectmade.model.DetailMovieMdl
import com.laylamac.finalprojectmade.model.DetailTvShowMdl
import com.laylamac.finalprojectmade.model.TvShowMdl
import com.laylamac.finalprojectmade.view.movie.MovieResponseMdl
import com.laylamac.finalprojectmade.view.tvshow.TvShowResponseMdl
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiMovie {
    @GET("discover/movie")
    fun loadMovie(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US"
    ): Call<MovieResponseMdl>

    @GET("discover/tv")
    fun loadTVShow(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US"
    ): Call<TvShowResponseMdl>

    @GET("movie/{id}")
    fun loadMovieDetail(
        @Path("id") id: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US"
    ): Call<DetailMovieMdl>

    @GET("tv/{id}")
    fun loadTVShowDetail(
        @Path("id") id: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US"
    ): Call<DetailTvShowMdl>

    @GET("search/movie")
    fun searchMovie(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): Call<MovieResponseMdl>

    @GET("search/tv")
    fun searchTVShow(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): Call<TvShowResponseMdl>

    @GET("discover/movie")
    fun todayReleaseMovie(
        @Query("api_key") apiKey: String,
        @Query("primary_release_date.gte") primaryReleaseDateGte: String,
        @Query("primary_release_date.lte") primaryReleaseDateLte: String
    ) : Call<MovieResponseMdl>
}