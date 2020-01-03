package com.laylamac.finalprojectmade.view.movie

import android.util.Log
import com.laylamac.finalprojectmade.api.ApiMovie
import com.laylamac.finalprojectmade.api.ApiRepository
import com.loopj.android.http.AsyncHttpClient.LOG_TAG
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviePresenter(private val mInterface: MovieInterface, private val mApi: ApiMovie) {

    fun loadMovie() {
        mInterface.showLoading()
        mApi.loadMovie(ApiRepository.TMDB_API_KEY).enqueue(object : Callback<MovieResponseMdl> {
            override fun onFailure(call: Call<MovieResponseMdl>, t: Throwable) {
                mInterface.hideLoading()
                Log.e(LOG_TAG, "${t.message}")
            }

            override fun onResponse(
                call: Call<MovieResponseMdl>,
                response: Response<MovieResponseMdl>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        Log.d("TEST123", "response $data")
                        mInterface.moviesData(data)
                    }
                    mInterface.hideLoading()
                }
            }


        })
    }

    fun searchMovie(query: String) {
        mInterface.showLoading()
        mApi.searchMovie(ApiRepository.TMDB_API_KEY, query)
            .enqueue(object : Callback<MovieResponseMdl> {
                override fun onFailure(call: Call<MovieResponseMdl>, t: Throwable) {
                    Log.e(LOG_TAG, "${t.message}")
                }

                override fun onResponse(
                    call: Call<MovieResponseMdl>,
                    response: Response<MovieResponseMdl>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        if (data != null) {
                            mInterface.moviesData(data)
                        }
                        mInterface.hideLoading()
                    }
                }

            })
    }

}
