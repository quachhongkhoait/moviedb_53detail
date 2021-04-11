package com.sun.moviedb_53.data.source.local

import com.sun.moviedb_53.data.source.local.HotMovieEntry.COL_ID
import com.sun.moviedb_53.data.source.local.HotMovieEntry.COL_IMAGE_POSTER
import com.sun.moviedb_53.data.source.local.HotMovieEntry.COL_RATE
import com.sun.moviedb_53.data.source.local.HotMovieEntry.COL_TAG_LINE
import com.sun.moviedb_53.data.source.local.HotMovieEntry.COL_TITLE
import com.sun.moviedb_53.data.source.local.HotMovieEntry.TABLE

class FavoriteTable {

    val DB_MOVIE = ("CREATE TABLE " + TABLE + "(" + COL_ID + " INTEGER PRIMARY KEY, "
            + COL_TITLE + " TEXT, " + COL_IMAGE_POSTER + " TEXT, " + COL_TAG_LINE + " TEXT, " + COL_RATE + " TEXT,)")
}

object HotMovieEntry {
    const val TABLE = "table_favorite"
    const val COL_ID = "id"
    const val COL_TITLE = " title"
    const val COL_IMAGE_POSTER = " poster_path"
    const val COL_TAG_LINE = " tagline"
    const val COL_RATE = " vote_average"
}
