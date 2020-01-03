package com.laylamac.finalprojectmade.db

data class DatabaseFavorite (
    var id: String,
    var title: String,
    var poster: String,
    var release: String,
    var description: String,
    var type: String
) {
    companion object{
        const val TABLE_FAV : String = "TABLE_FAVORITE"
        const val ID : String = "ID_"
        const val TITLE : String = "TITLE"
        const val POSTER : String = "POSTER"
        const val RELEASE : String = "RELEASE"
        const val DESCRIPTION : String = "DESCRIPTION"
        const val TYPE : String = "TYPE"


    }
}
