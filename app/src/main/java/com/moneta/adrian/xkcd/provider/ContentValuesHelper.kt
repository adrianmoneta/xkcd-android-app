package com.moneta.adrian.xkcd.provider

import android.content.ContentValues
import android.graphics.Bitmap
import com.moneta.adrian.xkcd.model.Comic
import java.nio.ByteBuffer
import androidx.core.app.NotificationCompat.getExtras
import java.io.ByteArrayOutputStream


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
        cv.put(ComicTable.FAVOURED_COLUMN, if(comic.isFavoured) 1 else 0)
        val bitmap = comic.imgBitmap ?: return cv

        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)
        val bytes = stream.toByteArray()

        cv.put(ComicTable.IMG_BLOB_COLUMN, bytes)
        return cv
    }

}