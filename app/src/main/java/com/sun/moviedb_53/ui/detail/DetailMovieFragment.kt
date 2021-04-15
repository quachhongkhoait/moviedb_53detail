package com.sun.moviedb_53.ui.detail

import android.annotation.SuppressLint
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import com.sun.moviedb_53.R
import com.sun.moviedb_53.base.BaseFragment
import com.sun.moviedb_53.data.model.MovieDetails
import com.sun.moviedb_53.data.source.repository.MovieRepository
import com.sun.moviedb_53.data.source.local.Favorite
import com.sun.moviedb_53.data.source.local.MovieLocalDataSource
import com.sun.moviedb_53.data.source.repository.FavoriteRepository
import com.sun.moviedb_53.extensions.loadFromUrl
import com.sun.moviedb_53.utils.Constant
import kotlinx.android.synthetic.main.fragment_detail_movie.*
import kotlin.math.roundToInt


class DetailMovieFragment : BaseFragment(), DetailMovieContact.View {

    private var isFavoriteMovie = false
    private var idMovieDetails: Int? = null
    private var favorite: Favorite? = null
    private var detailPresenter: MovieDetailPresenter? = null

    override fun getLayoutId() = R.layout.fragment_detail_movie

    override fun onViewCreated(view: View) {

        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
        val height: Int = displayMetrics.heightPixels
        constraintLayoutBar.apply {
            maxHeight = (height * 0.31).roundToInt()
            minHeight = (height * 0.31).roundToInt()
        }
    }

    override fun onEvent() {
        imageFavorite.setOnClickListener {
            favorite?.let {
                updateFavorite(it)
            }
        }
    }

    private fun updateFavorite(favorite: Favorite) {
        detailPresenter?.let {
            if (isFavoriteMovie) it.deleteFavorite(favorite.id)
            else it.insertFavorite(favorite)

            isFavoriteMovie = !isFavoriteMovie

            selectedFavorite()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailPresenter = MovieDetailPresenter(
                MovieRepository.instance,
                FavoriteRepository.getInstance(MovieLocalDataSource.getInstance(requireActivity()))
        )
        arguments?.let {
            idMovieDetails = it.getInt(ID_MOVIE_DETAIL)
        }
        detailPresenter?.let {
            it.setView(this)
            idMovieDetails?.let { id -> it.getMovieDetails(id) }
        }
    }

    override fun loadContentMovieOnSuccess(movieDetails: MovieDetails) {
        movieDetails.run {
            favorite = Favorite(id, title, imagePoster, tagLine, rate)
        }
        initDataMovieDetail(movieDetails)
    }

    @SuppressLint("SetTextI18n")
    private fun initDataMovieDetail(movieDetail: MovieDetails) {
        textViewTitle.text = movieDetail.title
        textViewOverview.text = resources.getString(R.string.overview) + movieDetail.description
        textViewRelease.text = movieDetail.releaseDate
        textViewTagLine.text = movieDetail.tagLine
        imageViewPoster.loadFromUrl(Constant.BASE_URL_IMAGE + movieDetail.imagePoster)
        imageViewBackground.loadFromUrl(Constant.BASE_URL_IMAGE + movieDetail.imageUrl)

        isFavoriteMovie = movieDetail.isFavorite

        selectedFavorite()
    }

    private fun selectedFavorite() {
        if (isFavoriteMovie) imageFavorite.setImageResource(R.drawable.ic_heart_red)
        else imageFavorite.setImageResource(R.drawable.ic_heart_default)
    }

    override fun onError(exception: Exception?) {
        Log.d(TAG, "onError: " + exception?.message)
    }

    companion object {
        private const val ID_MOVIE_DETAIL = "ID_MOVIE_DETAIL"

        fun newInstance(id: Int) = DetailMovieFragment().apply {
            arguments = bundleOf(ID_MOVIE_DETAIL to id)
        }
    }
}
