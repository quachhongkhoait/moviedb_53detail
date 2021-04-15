package com.sun.moviedb_53.ui.detail

import android.content.Context
import com.sun.moviedb_53.data.model.MovieDetails
import com.sun.moviedb_53.data.source.repository.MovieRepository
import com.sun.moviedb_53.data.source.local.Favorite
import com.sun.moviedb_53.data.source.remote.OnFetchDataJsonListener
import com.sun.moviedb_53.data.source.repository.FavoriteRepository

class MovieDetailPresenter(
        private val repository: MovieRepository,
        private val repositoryLocal: FavoriteRepository
) :
        DetailMovieContact.Presenter {

    private var view: DetailMovieContact.View? = null

    override fun getMovieDetails(id: Int) {
        repository.getMovieDetails(id, object : OnFetchDataJsonListener<MovieDetails> {
            override fun onSuccess(data: MovieDetails) {
                if (checkFavorite(data.id)) {
                    data.isFavorite = true
                }
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

    override fun deleteFavorite(id: Int) = repositoryLocal.deleteFavorite(id)

    override fun insertFavorite(favorite: Favorite) = repositoryLocal.saveFavorite(favorite)

    override fun checkFavorite(idMovieDetail: Int) = repositoryLocal.checkFavorite(idMovieDetail)

    override fun onStart() {
    }

    override fun onStop() {
        view = null
    }

    override fun setView(view: DetailMovieContact.View?) {
        this.view = view
    }
}
