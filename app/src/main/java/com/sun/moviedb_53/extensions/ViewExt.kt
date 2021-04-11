package com.sun.moviedb_53.extensions

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.sun.moviedb_53.R

fun View.toVisible() {
    this.visibility = View.VISIBLE
}

fun View.toGone() {
    this.visibility = View.GONE
}

@SuppressLint("UseCompatLoadingForDrawables")
fun ImageView.loadFromUrl(url: String) {
    Glide.with(this.context.applicationContext)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .skipMemoryCache(false)
        .into(this)
}
