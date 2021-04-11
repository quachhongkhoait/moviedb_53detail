package com.sun.moviedb_53.ui.detail

import com.sun.moviedb_53.data.model.HotMovie
import com.sun.moviedb_53.data.model.MovieDetails
import com.sun.moviedb_53.utils.BasePresenter

interface DetailMovieContact {

    interface View {
        fun loadContentMovieOnSuccess(movieDetails: MovieDetails)
        fun onError(exception: Exception?)
    }

    interface Presenter : BasePresenter<View> {
        fun getMovieDetails(id: Int)
        fun getVideoTrailer(idMovieDetails: Int)
        fun getListMovieRecommendations(idMovieDetails: Int)
        fun getCastsInMovieDetails(idMovieDetails: Int)
    }
}
