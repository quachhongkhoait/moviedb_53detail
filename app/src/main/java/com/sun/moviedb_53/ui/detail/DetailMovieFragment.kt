package com.sun.moviedb_53.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import com.sun.moviedb_53.R
import com.sun.moviedb_53.base.BaseFragment
import com.sun.moviedb_53.data.model.MovieDetails
import com.sun.moviedb_53.data.source.MovieRepository
import com.sun.moviedb_53.extensions.loadFromUrl
import com.sun.moviedb_53.utils.Constant
import kotlinx.android.synthetic.main.fragment_detail_movie.*
import kotlin.math.roundToInt


class DetailMovieFragment : BaseFragment(), DetailMovieContact.View {

    private var idMovieDetails: Int? = null

    val detailPresenter = MovieDetailPresenter(MovieRepository.instance)

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            idMovieDetails = it.getInt(ID_MOVIE_DETAIL)
        }
        detailPresenter.setView(this)
        idMovieDetails?.let { detailPresenter.getMovieDetails(it) }
    }

    companion object {

        private const val ID_MOVIE_DETAIL = "ID_MOVIE_DETAIL"

        fun newInstance(id: Int) = DetailMovieFragment().apply {
            arguments = bundleOf(ID_MOVIE_DETAIL to id)
        }
    }

    override fun loadContentMovieOnSuccess(movieDetails: MovieDetails) {
        initDataMovieDetail(movieDetails)
    }

    @SuppressLint("SetTextI18n")
    private fun initDataMovieDetail(movieDetail: MovieDetails) {
        textViewTitle.text = movieDetail.title
        textViewOverview.text = resources.getString(R.string.overview) + movieDetail.description
        imageViewPoster.loadFromUrl(Constant.BASE_URL_IMAGE + movieDetail.imagePoster)
        imageViewBackground.loadFromUrl(Constant.BASE_URL_IMAGE + movieDetail.imageUrl)
        textViewRelease.text = movieDetail.releaseDate
        textViewTagLine.text = movieDetail.tagLine
    }

    override fun onError(exception: Exception?) {
        Log.d(TAG, "onError: " + exception?.message)
    }
}