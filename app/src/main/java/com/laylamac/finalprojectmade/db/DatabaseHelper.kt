package com.laylamac.finalprojectmade.db

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.laylamac.finalprojectmade.MainActivity
import org.jetbrains.anko.db.*

class DatabaseHelper(context: Context, name: String = "databaseMovie.db") :
    ManagedSQLiteOpenHelper(context, name, null, DATA_VERSION) {

    private lateinit var mDatabase: SQLiteDatabase

    companion object {
        const val AUTHORITY = "com.laylamac.finalprojectmade"
        private const val DATA_VERSION = 1
        private var instance: DatabaseHelper? = null


        fun getInstance(context: Context): DatabaseHelper {
            if (instance == null) {
                instance = DatabaseHelper(context.applicationContext)
            }
            return instance as DatabaseHelper
        }
    }

    fun open() {
        mDatabase = instance!!.writableDatabase
    }

    fun queryMovieProvider(): Cursor {
        return mDatabase.query(
            DatabaseFavorite.TABLE_FAV,
            null,
            "${DatabaseFavorite.TYPE} = ?",
            arrayOf(MainActivity.MOVIE),
            null,
            null,
            null
        )
    }

    fun queryTvShowProvider(): Cursor {
        return mDatabase.query(
            DatabaseFavorite.TABLE_FAV,
            null,
            "${DatabaseFavorite.TYPE} = ?",
            arrayOf(MainActivity.TV),
            null,
            null,
            null
        )
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(
            DatabaseFavorite.TABLE_FAV, true,
            DatabaseFavorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            DatabaseFavorite.ID to TEXT,
            DatabaseFavorite.TITLE to TEXT,
            DatabaseFavorite.RELEASE to TEXT,
            DatabaseFavorite.DESCRIPTION to TEXT,
            DatabaseFavorite.TYPE to TEXT,
            DatabaseFavorite.POSTER to TEXT

        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(DatabaseFavorite.TABLE_FAV, true)
    }

}

val Context.database: DatabaseHelper
    get() = DatabaseHelper.getInstance(applicationContext)