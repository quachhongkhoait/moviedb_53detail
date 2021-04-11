package com.sun.moviedb_53.data.source.local

import com.sun.moviedb_53.data.source.MovieDataSource

class MovieLocalDataSource : MovieDataSource.Local {

    private object Holder {
        val INSTANCE = MovieLocalDataSource()
    }

    companion object {
        val instance: MovieLocalDataSource by lazy { Holder.INSTANCE }
    }
}
