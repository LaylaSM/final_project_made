package com.laylamac.finalprojectmade.api

import com.laylamac.finalprojectmade.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiRepository {
    var BASE_URL : String = BuildConfig.BASE_URL
    var TMDB_API_KEY : String = BuildConfig.TMDB_API_KEY
    var BASE_IMAGE : String = BuildConfig.POSTER_URL

    fun create (): ApiMovie{
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        return retrofit.create(ApiMovie::class.java)
    }

}
