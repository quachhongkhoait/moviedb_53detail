package com.sun.moviedb_53.ui.hot

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sun.moviedb_53.R
import com.sun.moviedb_53.data.model.HotMovie
import com.sun.moviedb_53.utils.Constant
import kotlinx.android.synthetic.main.item_hot_movie.view.*

class HotMovieAdapter : RecyclerView.Adapter<HotMovieAdapter.HotMovieViewHolder>() {

    private var listHotMovie = mutableListOf<HotMovie>()

    fun setData( newListHotMovie : MutableList<HotMovie>){
        listHotMovie = newListHotMovie
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotMovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_hot_movie,parent,false)
        return HotMovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listHotMovie.size
    }

    override fun onBindViewHolder(holder: HotMovieViewHolder, position: Int) {
        holder.populateItemRow(position)
    }

    inner class HotMovieViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun populateItemRow(position: Int){
            val itemHotMovie = listHotMovie.get(position)
            itemView.apply {
                tvHotMovieRate.setText((itemHotMovie.voteAverage).toString())
                Glide.with(imgHotMovie.getContext())
                    .load(Constant.BASE_URL_IMAGE + itemHotMovie.posterPath).into(imgHotMovie)
                rateBarHotMovie.rating = itemHotMovie.voteAverage.toFloat() / 2
                tvHotMovieTittle.setText(itemHotMovie.title)
            }
        }
    }

}