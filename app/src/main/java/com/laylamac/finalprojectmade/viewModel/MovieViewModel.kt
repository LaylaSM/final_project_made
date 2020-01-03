package com.laylamac.finalprojectmade.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.laylamac.finalprojectmade.view.movie.MovieResponseMdl


class MovieViewModel : ViewModel() {
    private var listMovies = MutableLiveData<MovieResponseMdl>()

    fun setMovies(movie: MovieResponseMdl) {
        listMovies.postValue(movie)
    }

    fun getMovies(): LiveData<MovieResponseMdl> {
        return listMovies
    }

}
