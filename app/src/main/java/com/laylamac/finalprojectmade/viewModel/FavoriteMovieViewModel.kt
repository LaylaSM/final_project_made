package com.laylamac.finalprojectmade.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.laylamac.finalprojectmade.model.MovieMdl

class FavoriteMovieViewModel : ViewModel() {

    private var favoriteMovie = MutableLiveData<List<MovieMdl>>()

    fun setFavoriteMovie (movie : List<MovieMdl>){
        favoriteMovie.postValue(movie)
    }

    fun getFavoriteMovie () : LiveData<List<MovieMdl>>{
        return favoriteMovie
    }

}
