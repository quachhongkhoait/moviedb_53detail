package com.sun.moviedb_53.ui.detail

import com.sun.moviedb_53.data.model.MovieDetails
import com.sun.moviedb_53.data.source.MovieRepository
import com.sun.moviedb_53.data.source.remote.OnFetchDataJsonListener

class MovieDetailPresenter(private val repository: MovieRepository) :
    DetailMovieContact.Presenter {

    private var view: DetailMovieContact.View? = null

    override fun getMovieDetails(id: Int) {
        repository.getMovieDetails(id, object : OnFetchDataJsonListener<MovieDetails> {
            override fun onSuccess(data: MovieDetails) {
                view?.loadContentMovieOnSuccess(data)
            }

            override fun onError(exception: Exception?) {
                view?.onError(exception)
            }
        })
    }

    override fun getVideoTrailer(idMovieDetails: Int) {
    }

    override fun getListMovieRecommendations(idMovieDetails: Int) {
    }

    override fun getCastsInMovieDetails(idMovieDetails: Int) {
    }

    override fun onStart() {
    }

    override fun onStop() {
        view = null
    }

    override fun setView(view: DetailMovieContact.View?) {
        this.view = view
    }
}
