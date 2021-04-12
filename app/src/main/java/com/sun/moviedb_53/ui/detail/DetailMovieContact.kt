package com.sun.moviedb_53.ui.detail

import android.content.Context
import com.sun.moviedb_53.data.model.HotMovie
import com.sun.moviedb_53.data.model.MovieDetails
import com.sun.moviedb_53.data.source.local.Favorite
import com.sun.moviedb_53.utils.BasePresenter

interface DetailMovieContact {

    interface View {
        fun loadContentMovieOnSuccess(movieDetails: MovieDetails)
        fun onError(exception: Exception?)
    }

    interface Presenter : BasePresenter<View> {
        fun setContext(context: Context?)
        fun getMovieDetails(id: Int)
        fun getVideoTrailer(idMovieDetails: Int)
        fun getListMovieRecommendations(idMovieDetails: Int)
        fun getCastsInMovieDetails(idMovieDetails: Int)
        fun deleteFavorite(id: Int)
        fun insertFavorite(favorite: Favorite)
    }
}
