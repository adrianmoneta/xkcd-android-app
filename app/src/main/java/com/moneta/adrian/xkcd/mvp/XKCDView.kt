package com.moneta.adrian.xkcd.mvp

import com.moneta.adrian.xkcd.model.Comic

interface XKCDView {

    fun onCount(count: Int)
    fun onCountError()

    fun onComic(comic: Comic)
    fun onComicError()

}