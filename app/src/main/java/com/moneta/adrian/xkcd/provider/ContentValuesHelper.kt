package com.moneta.adrian.xkcd.provider

import android.content.ContentValues
import android.graphics.Bitmap
import com.moneta.adrian.xkcd.model.Comic

object ContentValuesHelper {

    fun contentValuesForComic(comic: Comic) : ContentValues {
        val cv = ContentValues()
        cv.put(ComicTable.MONTH_COLUMN, comic.month)
        cv.put(ComicTable.NUM_COLUMN, comic.num)
        cv.put(ComicTable.LINK_COLUMN, comic.link)
        cv.put(ComicTable.YEAR_COLUMN, comic.year)
        cv.put(ComicTable.NEWS_COLUMN, comic.news)
        cv.put(ComicTable.SAFE_TITLE_COLUMN, comic.safe_title)
        cv.put(ComicTable.TRANSCRIPT_COLUMN, comic.transcript)
        cv.put(ComicTable.ALT_COLUMN, comic.alt)
        cv.put(ComicTable.IMG_COLUMN, comic.img)
        cv.put(ComicTable.TITLE_COLUMN, comic.title)
        cv.put(ComicTable.DAY_COLUMN, comic.day)
        return cv
    }

    fun contentValuesForComic(comic: Comic, bitmap: Bitmap) : ContentValues = contentValuesForComic(comic)
}