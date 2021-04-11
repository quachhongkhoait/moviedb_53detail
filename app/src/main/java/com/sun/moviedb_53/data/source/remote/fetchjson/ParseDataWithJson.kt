package com.sun.moviedb_53.data.source.remote.fetchjson

import android.util.Log
import com.edu.movie.data.model.GenresEntry
import com.sun.moviedb_53.data.model.HotMovieEntry
import com.sun.moviedb_53.data.model.MovieDetails
import com.sun.moviedb_53.data.model.MoviesDetailsEntry
import com.sun.moviedb_53.utils.KeyEntityTpye
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class ParseDataWithJson {

    @Throws(Exception::class)
    fun getJsonFromUrl(urlString: String?): String? {
        val url = URL(urlString)
        val httpURLConnection = url.openConnection() as HttpURLConnection
        httpURLConnection.connectTimeout = TIME_OUT
        httpURLConnection.readTimeout = TIME_OUT
        httpURLConnection.requestMethod = METHOD_GET
        httpURLConnection.doOutput = true
        httpURLConnection.connect()

        val bufferedReader = BufferedReader(InputStreamReader(url.openStream()))
        val stringBuilder = StringBuilder()
        var line: String?
        while (bufferedReader.readLine().also { line = it } != null) {
            stringBuilder.append(line)
        }
        bufferedReader.close()
        httpURLConnection.disconnect()
        return stringBuilder.toString()
    }

    fun parseJsonToData(jsonObject: JSONObject?, keyEntityType: KeyEntityTpye): Any? {

        try {
            return when (keyEntityType) {
                KeyEntityTpye.MOVIE_ITEM -> {
                    parseJsonToList(
                        jsonObject?.getJSONArray(HotMovieEntry.MOVIE),
                        keyEntityType
                    )
                }
                KeyEntityTpye.MOVIE_DETAIL -> {
                    parseJsonToObject(
                        jsonObject,
                        keyEntityType
                    )
                }
                KeyEntityTpye.GENRES -> {
                    parseJsonToList(
                        jsonObject?.getJSONArray(GenresEntry.LIST_GENRES),
                        keyEntityType
                    )
                }
                else -> Log.d("nnn", "parseJsonToObject: ")
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return null
    }

    @Throws(Exception::class)
    private fun parseJsonToList(jsonArray: JSONArray?, typeModel: KeyEntityTpye): Any {
        val data = mutableListOf<Any?>()
        for (i in 0 until (jsonArray?.length() ?: 0)) {
            val jsonObject = jsonArray?.getJSONObject(i)
            data.add(parseJsonToObject(jsonObject, typeModel))
        }
        return data.filterNotNull()
    }

    private fun parseJsonToObject(jsonObject: JSONObject?, keyEntityType: KeyEntityTpye): Any? {
        try {
            jsonObject?.let {
                return when (keyEntityType) {
                    KeyEntityTpye.MOVIE_ITEM -> {
                        ParseJson().movieParseJson(it)
                    }
                    KeyEntityTpye.MOVIE_DETAIL -> {
                        ParseJson().movieDetailParseJson(it)
                    }
                    KeyEntityTpye.GENRES -> {
                        ParseJson().parseJsonToGenres(it)
                    }
                    else -> Log.d("nnn", "parseJsonToObject: ")
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return null
    }

    companion object {
        private const val TIME_OUT = 15000
        private val METHOD_GET: String? = "GET"
    }
}
