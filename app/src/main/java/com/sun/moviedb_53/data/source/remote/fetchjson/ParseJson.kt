package com.sun.moviedb_53.data.source.remote.fetchjson

import com.edu.movie.data.model.Genres
import com.edu.movie.data.model.GenresEntry
import com.sun.moviedb_53.data.model.HotMovie
import com.sun.moviedb_53.data.model.HotMovieEntry
import com.sun.moviedb_53.data.model.MovieDetails
import com.sun.moviedb_53.data.model.MoviesDetailsEntry
import com.sun.moviedb_53.utils.KeyEntityTpye
import org.json.JSONObject

class ParseJson {

    fun movieParseJson(jsonObject: JSONObject): HotMovie {
        return HotMovie(
            id = jsonObject.getInt(HotMovieEntry.ID),
            voteAverage = jsonObject.getDouble(HotMovieEntry.VOTE),
            title = jsonObject.getString(HotMovieEntry.TITLE),
            posterPath = jsonObject.getString(HotMovieEntry.URL_IMAGE)
        )
    }

    fun movieDetailParseJson(jsonObject: JSONObject): MovieDetails {

        val parseDataWithJson = ParseDataWithJson()
        jsonObject.run {

            return MovieDetails(
                id = getInt(MoviesDetailsEntry.ID),
                title = getString(MoviesDetailsEntry.TITLE),
                imageUrl = getString(MoviesDetailsEntry.IMAGE_URL),
                rate = getDouble(MoviesDetailsEntry.RATE),
                productionCountry = getString(MoviesDetailsEntry.PRODUCTION_COUNTRY),
                description = getString(MoviesDetailsEntry.DESCRIPTION),
                imagePoster = getString(MoviesDetailsEntry.IMAGE_POSTER),
                genres = getString(MoviesDetailsEntry.LIST_GENRES),
                releaseDate = getString(MoviesDetailsEntry.RELEASE_DATE),
                tagLine = getString(MoviesDetailsEntry.TAG_LINE)
            )
        }
    }

    @Throws(Exception::class)
    fun parseJsonToGenres(jsonObject: JSONObject?): Genres? =
        jsonObject?.run {
            Genres(
                getInt(GenresEntry.ID),
                getString(GenresEntry.NAME)
            )
        }
}
