package com.moneta.adrian.xkcd.service


interface FavouritesService {
    fun favourComic(issueNumber: Int)
    fun unfavourComic(issueNumber: Int)
}