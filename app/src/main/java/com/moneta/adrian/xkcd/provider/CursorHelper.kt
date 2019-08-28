package com.moneta.adrian.xkcd.provider

import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.moneta.adrian.xkcd.model.Comic

object CursorHelper {
    fun readComic(cursor: Cursor) : Comic {
        val bytes = cursor.getBlob(cursor.getColumnIndex(ComicTable.IMG_BLOB_COLUMN))
        val bitmap: Bitmap? =
            if(null != bytes) BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            else null

        return Comic(
            cursor.getString(cursor.getColumnIndex(ComicTable.MONTH_COLUMN)),
            cursor.getInt(cursor.getColumnIndex(ComicTable.NUM_COLUMN)),
            cursor.getString(cursor.getColumnIndex(ComicTable.LINK_COLUMN)),
            cursor.getString(cursor.getColumnIndex(ComicTable.YEAR_COLUMN)),
            cursor.getString(cursor.getColumnIndex(ComicTable.NEWS_COLUMN)),
            cursor.getString(cursor.getColumnIndex(ComicTable.SAFE_TITLE_COLUMN)),
            cursor.getString(cursor.getColumnIndex(ComicTable.TRANSCRIPT_COLUMN)),
            cursor.getString(cursor.getColumnIndex(ComicTable.ALT_COLUMN)),
            cursor.getString(cursor.getColumnIndex(ComicTable.IMG_COLUMN)),
            cursor.getString(cursor.getColumnIndex(ComicTable.TITLE_COLUMN)),
            cursor.getString(cursor.getColumnIndex(ComicTable.DAY_COLUMN)),
            cursor.getInt(cursor.getColumnIndex(ComicTable.FAVOURED_COLUMN)) == 1,
            bitmap)
    }

}