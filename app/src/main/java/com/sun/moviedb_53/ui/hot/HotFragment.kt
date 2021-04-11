package com.sun.moviedb_53.ui.hot

import android.annotation.SuppressLint
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.sun.moviedb_53.R
import com.sun.moviedb_53.base.BaseFragment
import com.sun.moviedb_53.data.model.HotMovie
import com.sun.moviedb_53.data.source.MovieRepository
import com.sun.moviedb_53.utils.HotMovieType
import kotlinx.android.synthetic.main.fragment_hot.*

@Suppress("DEPRECATION")
class HotFragment : BaseFragment(), HotMovieContact.View {

    val hotMoviePresenter = HotMoviePresenter(MovieRepository.instance)
    private var adapterHotMovie = HotMovieAdapter()

    override fun getLayoutId() = R.layout.fragment_hot

    override fun onViewCreated(view: View) {
        initView()
        initData()
    }

    private fun initView(){
        rcvHotMovie.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(activity)
        rcvHotMovie.layoutManager = linearLayoutManager
        rcvHotMovie.adapter = adapterHotMovie
        btnPopular.setOnClickListener{
            setBackgroundButtonClick(btnPopular,btnTopRate,btnUpComming)
            hotMoviePresenter.getHotMovies(HotMovieType.POPULAR)
        }
        btnTopRate.setOnClickListener{
            setBackgroundButtonClick(btnTopRate,btnPopular,btnUpComming)
            hotMoviePresenter.getHotMovies(HotMovieType.TOP_RATED)
        }
        btnUpComming.setOnClickListener{
            setBackgroundButtonClick(btnUpComming,btnPopular,btnTopRate)
            hotMoviePresenter.getHotMovies(HotMovieType.UP_COMING)
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setBackgroundButtonClick(buttonClick: Button, buttonNotClick1: Button, buttonNotClick2: Button){
        buttonClick.background = resources.getDrawable(R.drawable.custom_hot_screen_button)
        buttonClick.setTextColor(resources.getColor(R.color.black))
        buttonNotClick1.background = resources.getDrawable(R.drawable.custom_hot_screen_button_not_click)
        buttonNotClick1.setTextColor(resources.getColor(R.color.white))
        buttonNotClick2.background = resources.getDrawable(R.drawable.custom_hot_screen_button_not_click)
        buttonNotClick2.setTextColor(resources.getColor(R.color.white))
    }

    private fun initData(){
        hotMoviePresenter.setView(this)
        hotMoviePresenter.onStart()
    }

    companion object {
        fun newInstance() = HotFragment()
    }

    override fun onGetMoviesSuccess(movies: MutableList<HotMovie>) {
        adapterHotMovie.setData(movies)
    }

    override fun onError(exception: Exception?) {
        Toast.makeText(activity, exception?.message, Toast.LENGTH_SHORT).show()
    }
}
