package com.moneta.adrian.xkcd.service.implementations

import android.content.Context
import com.moneta.adrian.xkcd.model.Comic
import com.moneta.adrian.xkcd.provider.ContentValuesHelper
import com.moneta.adrian.xkcd.provider.CursorHelper
import com.moneta.adrian.xkcd.provider.UriHelper
import com.moneta.adrian.xkcd.service.ComicStorageService

class ComicContentProviderService(private val context : Context) : ComicStorageService {
    override fun putIssue(comic: Comic) {
        context.contentResolver.insert(
            UriHelper.buildComicUri(comic.num),
            ContentValuesHelper.contentValuesForComic(comic))
    }

    override fun getIssue(issueNumber: Int, complete: (Comic?) -> Unit) {
        val cursor = context.contentResolver.query(
            UriHelper.buildComicUri(issueNumber),
            null,
            null,
            null,
            null)
        cursor ?: return complete(null)

        val comic = if(cursor.moveToFirst()) CursorHelper.readComic(cursor) else null
        cursor.close()
        complete(comic)
    }
}

