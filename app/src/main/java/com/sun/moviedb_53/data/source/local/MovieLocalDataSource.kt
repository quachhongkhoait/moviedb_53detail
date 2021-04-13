package com.sun.moviedb_53.data.source.local

import android.content.Context
import com.sun.moviedb_53.data.source.MovieDataSource
import com.sun.moviedb_53.data.source.local.database.DatabaseHelper
import com.sun.moviedb_53.data.source.local.database.dao.FavoriteDaoImpl
import com.sun.moviedb_53.data.source.remote.OnFetchDataJsonListener

class MovieLocalDataSource: MovieDataSource.Local {// private constructor(private val favoriteDaoImpl: FavoriteDaoImpl)

    override fun saveMovie(favorite: Favorite, isSave: Boolean) {}

    override fun getListFavorite(listener: OnFetchDataJsonListener<MutableList<Favorite>>) {}

    override fun deleteFavorite(idMovie: Int, isDelete: Boolean) {}

    override fun checkFavorite(idMovie: Int, isCheck: Boolean) {}

//    companion object {
//        var instance: MovieLocalDataSource? = null
//
//        fun getInstance(context: Context): MovieDataSource.Local =
//                instance ?: MovieLocalDataSource(
//                        FavoriteDaoImpl.getInstance(
//                                DatabaseHelper.getDatabaseHelper(context))
//                ).also { instance = it }
//    }
}
