package com.moneta.adrian.xkcd.provider

import android.database.Cursor
import com.moneta.adrian.xkcd.model.Comic

object CursorHelper {
    fun readComic(cursor: Cursor) : Comic {
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
            cursor.getString(cursor.getColumnIndex(ComicTable.DAY_COLUMN))
        )
    }
}