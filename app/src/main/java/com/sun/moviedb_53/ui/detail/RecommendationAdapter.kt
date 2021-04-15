package com.sun.moviedb_53.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sun.moviedb_53.R
import com.sun.moviedb_53.data.model.HotMovie
import com.sun.moviedb_53.extensions.loadFromUrl
import com.sun.moviedb_53.utils.Constant
import kotlinx.android.synthetic.main.item_recommendations.view.*

class RecommendationAdapter(private val onItemClick: (Int) -> Unit) :
        RecyclerView.Adapter<RecommendationAdapter.RecommendViewHolder>() {

    private var listMovie = listOf<HotMovie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_recommendations, parent, false)
        return RecommendViewHolder(view, onItemClick)
    }

    override fun getItemCount() = listMovie.size

    override fun onBindViewHolder(holder: RecommendViewHolder, position: Int) {
        holder.bindData(listMovie[position])
    }

    fun setData(newList: List<HotMovie>) {
        listMovie = newList
        notifyDataSetChanged()
    }

    class RecommendViewHolder(itemView: View, private val onItemClick: (Int) -> Unit) :
            RecyclerView.ViewHolder(itemView) {

        fun bindData(hotMovie: HotMovie) {
            itemView.apply {
                textRecommendation.text = hotMovie.title
                imageRecommendation.loadFromUrl(Constant.BASE_URL_IMAGE + hotMovie.posterPath)
                setOnClickListener {
                    onItemClick(position)
                }
            }
        }
    }
}