package com.sun.moviedb_53.data.source.repository

import com.sun.moviedb_53.data.source.MovieDataSource
import com.sun.moviedb_53.data.source.local.Favorite

class FavoriteRepository private constructor(
    private val local: MovieDataSource.Local
) {

    fun saveFavorite(favorite: Favorite) = local.saveMovie(favorite)

    fun deleteFavorite(idMovie: Int) = local.deleteFavorite(idMovie)

    fun checkFavorite(idMovie: Int) = local.checkFavorite(idMovie)

    fun getListFavorite() = local.getListFavorite()

    companion object {
        private var instance: FavoriteRepository? = null

        fun getInstance(
            local: MovieDataSource.Local
        ): FavoriteRepository {
            return instance ?: FavoriteRepository(local).also {
                instance = it
            }
        }
    }
}