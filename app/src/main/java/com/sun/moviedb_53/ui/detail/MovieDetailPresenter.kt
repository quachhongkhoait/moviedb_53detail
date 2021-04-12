package com.sun.moviedb_53.ui.detail

import android.content.Context
import com.sun.moviedb_53.data.model.MovieDetails
import com.sun.moviedb_53.data.source.MovieRepository
import com.sun.moviedb_53.data.source.local.FavoriteController
import com.sun.moviedb_53.data.source.local.Favorite
import com.sun.moviedb_53.data.source.remote.OnFetchDataJsonListener

class MovieDetailPresenter(private val repository: MovieRepository) :
    DetailMovieContact.Presenter {

    private var context: Context? = null
    private var view: DetailMovieContact.View? = null
    private var favoriteController: FavoriteController? = null

    override fun getMovieDetails(id: Int) {
        repository.getMovieDetails(id, object : OnFetchDataJsonListener<MovieDetails> {
            override fun onSuccess(data: MovieDetails) {
                if (favoriteController?.checkFavorite(data.id) == true) {
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

    override fun deleteFavorite(id: Int) {
        favoriteController?.deleteFavorite(id)
    }

    override fun insertFavorite(favorite: Favorite) {
        favoriteController?.saveFavoriteData(favorite)
    }

    override fun onStart() {
    }

    override fun onStop() {
        view = null
    }

    override fun setContext(context: Context?) {
        context?.let {
            this.context = it
            favoriteController = FavoriteController(it)
        }

    }

    override fun setView(view: DetailMovieContact.View?) {
        this.view = view
    }
}
