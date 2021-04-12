package com.sun.moviedb_53.data.source.local

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


private const val DATABASE_VERSION = 1
private const val DATABASE_NAME = "movie.db"

const val TAG = "nnn"

class TableSQLiteHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(FavoriteEntry.DB_MOVIE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.apply {
            execSQL("DROP TABLE IF EXISTS " + FavoriteEntry.DB_MOVIE)
            onCreate(this)
        }
    }

    fun insertData(values: ContentValues?): Boolean {
        val db = this.writableDatabase
        val result = db.insert(FavoriteEntry.TABLE, null, values)
        return if (result == -1L) {
            Log.d(TAG, "failed to save data!")
            false
        } else {
            Log.d(TAG, "save data successful")
            true
        }
    }

    fun getFavoriteDetail(id: Int): Favorite? {
        val favorite: Favorite
        val db = this.readableDatabase
        val columns = arrayOf(
            FavoriteEntry.COL_ID,
            FavoriteEntry.COL_TITLE,
            FavoriteEntry.COL_IMAGE_POSTER,
            FavoriteEntry.COL_TAG_LINE,
            FavoriteEntry.COL_RATE
        )
        val selection: String = FavoriteEntry.COL_ID + " =? "
        val args = arrayOf(id.toString())
        val cursor: Cursor? = db.query(
            FavoriteEntry.TABLE, columns,
            selection, args, null, null, null
        )
        if (cursor != null && cursor.moveToFirst()) {
            favorite = Favorite(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(1),
                cursor.getString(1),
                cursor.getDouble(1)
            )
            return favorite
        }
        return null
    }

    fun checkFavorite(id: Int): Boolean {

        val columns = arrayOf(FavoriteEntry.COL_ID)

        val db = this.readableDatabase

        val selection: String = FavoriteEntry.COL_ID + " =? "

        val args = arrayOf(id.toString())

        val cursor = db.query(
            FavoriteEntry.TABLE,
            columns,
            selection,
            args,
            null,
            null,
            null
        )
        val count = cursor.count
        cursor.close()
        db.close()
        if (count > 0) {
            Log.d(TAG, "return true")
            return true
        }
        return false
    }

    fun deleteFavorite(id: Int): Boolean {
        val db = this.writableDatabase
        return db.delete(
            FavoriteEntry.TABLE,
            FavoriteEntry.COL_ID + " =? ",
            arrayOf(id.toString())
        ) > 0
    }
}
