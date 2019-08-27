package com.moneta.adrian.xkcd.provider

import android.net.Uri

object UriHelper {
    const val COMIC_PATH = "comic"
    const val COMICS_PATH = "comics"
    const val BITMAP_PATH = "bitmap"
    const val FAVOURITES_PATH = "favourites"
    const val CONTENT_AUTHORITY = "com.moneta.adrian.xkcd"

    private val BASE_CONTENT_URI = Uri.parse("content://$CONTENT_AUTHORITY")!!
    private val BASE_COMIC_CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(COMIC_PATH).build()
    private val BASE_BITMAP_CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(BITMAP_PATH).build()


    val COMICS_CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(COMICS_PATH).build()
    val FAVOURITES_CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(FAVOURITES_PATH).build()

    fun buildComicUri(issueNumber: Int) = BASE_COMIC_CONTENT_URI
        .buildUpon()
        .appendPath(issueNumber.toString())
        .build()

    fun buildBitmapUri(issueNumber: Int) = BASE_BITMAP_CONTENT_URI
        .buildUpon()
        .appendPath(issueNumber.toString())
        .build()

}
