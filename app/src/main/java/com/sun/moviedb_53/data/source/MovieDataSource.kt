package com.sun.moviedb_53.data.source

import com.sun.moviedb_53.data.model.HotMovie
import com.sun.moviedb_53.data.source.remote.OnFetchDataJsonListener
import com.sun.moviedb_53.utils.HotMovieType
import com.sun.moviedb_53.utils.TypeMovieDetail

interface MovieDataSource {
    /**
     * Local
     */
    interface Local

    /**
     * Remote
     */
    interface Remote {
        fun getHotMovies(
            page : Int,
            hotMovieType : HotMovieType,
            listener: OnFetchDataJsonListener<MutableList<HotMovie>>
        )
        fun <T> getDataInMovieDetails(
            idMovie: Int,
            typeEndPoint: TypeMovieDetail,
            listener: OnFetchDataJsonListener<T>
        )
    }
}
