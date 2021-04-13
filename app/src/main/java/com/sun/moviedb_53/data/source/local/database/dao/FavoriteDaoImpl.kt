package com.sun.moviedb_53.data.source.local.database.dao

import android.content.ContentValues
import com.sun.moviedb_53.data.source.local.Favorite
import com.sun.moviedb_53.data.source.local.database.DatabaseHelper
import com.sun.moviedb_53.data.source.local.database.table.FavoriteTable

class FavoriteDaoImpl private constructor(private val databaseHelper: DatabaseHelper) : FavoriteDao {

    override fun insertFavorite(favorite: Favorite): Boolean {
        val values = ContentValues().apply {
            put(FavoriteTable.COL_ID, favorite.id)
            put(FavoriteTable.COL_TITLE, favorite.title)
            put(FavoriteTable.COL_IMAGE_POSTER, favorite.imagePoster)
            put(FavoriteTable.COL_TAG_LINE, favorite.tagLine)
            put(FavoriteTable.COL_RATE, favorite.rate)
        }
        val result = databaseHelper.writableDatabase.insert(FavoriteTable.TABLE_NAME, null, values)
        return result != -1L
    }

    override fun deleteFavorite(id: Int): Boolean {
        return databaseHelper.writableDatabase.delete(
                FavoriteTable.TABLE_NAME,
                FavoriteTable.COL_ID + " =? ",
                arrayOf(id.toString())
        ) > 0
    }

    override fun checkFavorite(id: Int): Boolean {
        val columns = arrayOf(FavoriteTable.COL_ID)
        val selection: String = FavoriteTable.COL_ID + " =? "

        val args = arrayOf(id.toString())

        val cursor = databaseHelper.readableDatabase.query(
                FavoriteTable.TABLE_NAME,
                columns,
                selection,
                args,
                null,
                null,
                null
        )
        val count = cursor.count
        cursor.close()
        databaseHelper.close()
        return count > 0
    }

    override fun getListFavorite(): MutableList<Favorite> {
        val list = mutableListOf<Favorite>()
        val cursor = databaseHelper.readableDatabase.query(
                FavoriteTable.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
        )
        cursor?.run {
            while (moveToNext()) {
                list.add(
                        Favorite(
                                id = getInt(getColumnIndex(FavoriteTable.COL_ID)),
                                title = getString(getColumnIndex(FavoriteTable.COL_TITLE)),
                                imagePoster = getString(getColumnIndex(FavoriteTable.COL_IMAGE_POSTER)),
                                tagLine = getString(getColumnIndex(FavoriteTable.COL_TAG_LINE)),
                                rate = getDouble(getColumnIndex(FavoriteTable.COL_RATE)),
                        )
                )
            }
        }
        return list
    }

    companion object {
        private var instance: FavoriteDaoImpl? = null

        fun getInstance(databaseHelper: DatabaseHelper): FavoriteDaoImpl =
                instance ?: FavoriteDaoImpl(databaseHelper).also { instance = it }
    }
}
