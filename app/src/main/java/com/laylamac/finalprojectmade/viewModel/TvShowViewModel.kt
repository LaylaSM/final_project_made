package com.laylamac.finalprojectmade.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.laylamac.finalprojectmade.view.tvshow.TvShowResponseMdl

class TvShowViewModel : ViewModel() {
    private var listTvShow = MutableLiveData<TvShowResponseMdl>()

    fun setTvShows(tvShow: TvShowResponseMdl) {
        listTvShow.postValue(tvShow)
    }

    fun getTvShows(): LiveData<TvShowResponseMdl> {
        return listTvShow
    }

}
