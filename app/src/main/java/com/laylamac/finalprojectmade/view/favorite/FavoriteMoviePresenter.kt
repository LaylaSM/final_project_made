package com.laylamac.finalprojectmade.view.favorite

import android.content.Context
import com.laylamac.finalprojectmade.MainActivity
import com.laylamac.finalprojectmade.db.DatabaseFavorite
import com.laylamac.finalprojectmade.db.database
import com.laylamac.finalprojectmade.model.MovieMdl
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavoriteMoviePresenter(private val mInterface: FavoriteMovieInterface) {
    private var listMovie = mutableListOf<MovieMdl>()

    fun getFavMovie(context: Context?) {
        mInterface.showLoading()
        context?.database?.use {
            listMovie.clear()

            val result = select(
                DatabaseFavorite.TABLE_FAV
            ).whereArgs("${DatabaseFavorite.TYPE} = '${MainActivity.MOVIE}'")
            val listFav = result.parseList(classParser<DatabaseFavorite>())
            for (i in listFav.indices){
                listMovie.add(MovieMdl(
                    listFav[i].id,
                    listFav[i].title,
                    listFav[i].poster,
                    listFav[i].description,
                    listFav[i].release

                ))
            }
        }
        mInterface.hideLoading()
        mInterface.favoriteMovie(listMovie)
    }

}
