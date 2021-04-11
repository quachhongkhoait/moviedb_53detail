package com.sun.moviedb_53.data.source.remote

import com.sun.moviedb_53.data.model.HotMovie
import com.sun.moviedb_53.data.model.HotMovieEntry
import com.sun.moviedb_53.data.source.MovieDataSource
import com.sun.moviedb_53.data.source.remote.fetchjson.GetJsonFromUrl
import com.sun.moviedb_53.utils.Constant
import com.sun.moviedb_53.utils.HotMovieType
import com.sun.moviedb_53.utils.KeyEntityTpye
import com.sun.moviedb_53.utils.TypeMovieDetail

@Suppress("DEPRECATION")
class MovieRemoteDataSource : MovieDataSource.Remote {

    private val endPointParams = Constant.BASE_API_KEY + Constant.BASE_LANGUAGE

    private object Holder {
        val INSTANCE = MovieRemoteDataSource()
    }

    override fun getHotMovies(
        page: Int,
        hotMovieType: HotMovieType,
        listener: OnFetchDataJsonListener<MutableList<HotMovie>>
    ) {
        val baseUrl = Constant.BASE_URL + MOVIE_TYPE + hotMovieType.path +
                Constant.BASE_API_KEY +
                Constant.BASE_LANGUAGE +
                Constant.BASE_PAGE + page
        GetJsonFromUrl(listener, KeyEntityTpye.MOVIE_ITEM).execute(baseUrl)
    }

    override fun <T> getDataInMovieDetails(
        idMovie: Int,
        typeEndPoint: TypeMovieDetail,
        listener: OnFetchDataJsonListener<T>
    ) {
        val stringUrl =
            Constant.BASE_URL + MOVIE_TYPE + idMovie + typeEndPoint.path + endPointParams
        GetJsonFromUrl(listener, KeyEntityTpye.MOVIE_DETAIL).execute(stringUrl)
    }

    companion object {
        val instance: MovieRemoteDataSource by lazy { Holder.INSTANCE }
        private const val MOVIE_TYPE = "movie/"
    }
}
