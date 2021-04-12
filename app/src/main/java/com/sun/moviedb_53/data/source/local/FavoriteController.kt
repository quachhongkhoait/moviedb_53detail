package com.sun.moviedb_53.data.source.local

import android.content.ContentValues
import android.content.Context





class FavoriteController(context: Context) {

    var tableSQLiteHelper: TableSQLiteHelper = TableSQLiteHelper(context)

    fun saveFavoriteData(favorite: Favorite): Boolean {
        val values = ContentValues().apply {
            put(FavoriteEntry.COL_ID, favorite.id)
            put(FavoriteEntry.COL_TITLE, favorite.title)
            put(FavoriteEntry.COL_IMAGE_POSTER, favorite.imagePoster)
            put(FavoriteEntry.COL_TAG_LINE, favorite.tagLine)
            put(FavoriteEntry.COL_RATE, favorite.rate)
        }
        return tableSQLiteHelper.insertData(values)
    }
    fun checkFavorite(id: Int): Boolean {
        return tableSQLiteHelper.checkFavorite(id)
    }

    fun deleteFavorite(id: Int): Boolean {
        return tableSQLiteHelper.deleteFavorite(id)
    }
}