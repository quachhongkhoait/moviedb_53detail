package com.sun.moviedb_53.data.source.local

data class Favorite(
    val id: Int,
    val title: String,
    val imagePoster: String,
    val tagLine: String,
    val rate: Double
)

object FavoriteEntry {
    const val TABLE = "table_favorite"
    const val COL_ID = "id"
    const val COL_TITLE = " title"
    const val COL_IMAGE_POSTER = " poster_path"
    const val COL_TAG_LINE = " tagline"
    const val COL_RATE = " vote_average"
    const val DB_MOVIE = ("CREATE TABLE " + TABLE + "(" + COL_ID + " INTEGER PRIMARY KEY, "
            + COL_TITLE + " TEXT, " + COL_IMAGE_POSTER + " TEXT, " + COL_TAG_LINE + " TEXT, " + COL_RATE + " REAL)")
}
