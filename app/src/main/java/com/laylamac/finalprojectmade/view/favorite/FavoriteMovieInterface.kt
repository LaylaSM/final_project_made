package com.laylamac.finalprojectmade.view.favorite

import com.laylamac.finalprojectmade.model.MovieMdl

interface FavoriteMovieInterface {
    fun showLoading()
    fun hideLoading()
    fun favoriteMovie (movie : List<MovieMdl>)
}
