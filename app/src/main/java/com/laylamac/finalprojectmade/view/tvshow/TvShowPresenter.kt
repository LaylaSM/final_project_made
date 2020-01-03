package com.laylamac.finalprojectmade.view.tvshow

import android.util.Log
import com.laylamac.finalprojectmade.api.ApiMovie
import com.laylamac.finalprojectmade.api.ApiRepository
import com.loopj.android.http.AsyncHttpClient.LOG_TAG
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvShowPresenter(private val mInterface: TvShowInterface, private val mApi: ApiMovie) {

    fun loadTvShow() {
        mInterface.showLoading()
        mApi.loadTVShow(ApiRepository.TMDB_API_KEY).enqueue(object : Callback<TvShowResponseMdl> {
            override fun onFailure(call: Call<TvShowResponseMdl>, t: Throwable) {
                mInterface.hideLoading()
                Log.e(LOG_TAG, "${t.message}")
            }

            override fun onResponse(
                call: Call<TvShowResponseMdl>,
                response: Response<TvShowResponseMdl>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        mInterface.tvShowData(data)
                    }
                    mInterface.hideLoading()
                }
            }

        })
    }

    fun searchTvShow(query: String) {
        mInterface.showLoading()
        mApi.searchTVShow(ApiRepository.TMDB_API_KEY, query)
            .enqueue(object : Callback<TvShowResponseMdl> {
                override fun onFailure(call: Call<TvShowResponseMdl>, t: Throwable) {
                    mInterface.hideLoading()
                    Log.e(LOG_TAG, "${t.message}")
                }

                override fun onResponse(
                    call: Call<TvShowResponseMdl>,
                    response: Response<TvShowResponseMdl>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        if (data != null) {
                            mInterface.tvShowData(data)
                        }
                        mInterface.hideLoading()
                    }
                }

            })
    }

}
