package com.moneta.adrian.xkcd.model

import android.graphics.Bitmap

data class Comic(
    val month: String,
    val num: Int,
    val link: String,
    val year: String,
    val news: String,
    val safe_title: String,
    val transcript: String,
    val alt: String,
    val img: String,
    val title: String,
    val day: String,
    var isFavoured: Boolean = false,
    var imgBitmap: Bitmap? = null)