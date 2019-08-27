package com.moneta.adrian.xkcd.service.implementations

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.moneta.adrian.xkcd.model.Comic
import com.moneta.adrian.xkcd.provider.ContentValuesHelper
import com.moneta.adrian.xkcd.provider.UriHelper
import com.moneta.adrian.xkcd.service.StorageService
import com.moneta.adrian.xkcd.utils.TAG

class ContentProviderStorageService(private val context : Context) : StorageService {

    override fun putIssue(comic: Comic) {
        context.contentResolver.insert(
            UriHelper.buildComicUri(comic.num),
            ContentValuesHelper.contentValuesForComic(comic))
    }

}

