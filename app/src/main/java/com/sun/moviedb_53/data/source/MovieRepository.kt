package com.sun.moviedb_53.data.source

import com.sun.moviedb_53.data.model.HotMovie
import com.sun.moviedb_53.data.model.MovieDetails
import com.sun.moviedb_53.data.source.local.Favorite
import com.sun.moviedb_53.data.source.local.MovieLocalDataSource
import com.sun.moviedb_53.data.source.remote.MovieRemoteDataSource
import com.sun.moviedb_53.data.source.remote.OnFetchDataJsonListener
import com.sun.moviedb_53.utils.HotMovieType
import com.sun.moviedb_53.utils.TypeMovieDetail

class MovieRepository private constructor(
    private val remote: MovieDataSource.Remote,
    private val local: MovieDataSource.Local
) {

    private object Holder {
        val INSTANCE = MovieRepository(
            remote = MovieRemoteDataSource.instance,
            local = MovieLocalDataSource()
        )
    }

    fun getMovie(
        page: Int,
        hotMovieType: HotMovieType,
        listener: OnFetchDataJsonListener<MutableList<HotMovie>>
    ) {
        remote.getHotMovies(page, hotMovieType, listener)
    }

    fun getMovieDetails(id: Int, listener: OnFetchDataJsonListener<MovieDetails>) {
        remote.getDataInMovieDetails(id, TypeMovieDetail.MOVIE_DETAILS, listener)
    }

    fun saveFavorite(favorite: Favorite){
        return
    }

    companion object {
        val instance: MovieRepository by lazy { Holder.INSTANCE }
    }
}
