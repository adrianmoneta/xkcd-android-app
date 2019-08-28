package com.moneta.adrian.xkcd.service.implementations

import com.moneta.adrian.xkcd.service.ComicStorageService
import com.moneta.adrian.xkcd.service.FavouritesService

class FavouritesServiceImplementation(private val comicStorageService: ComicStorageService) : FavouritesService {

    override fun unfavourComic(issueNumber: Int) = setComicFavoured(issueNumber, false)

    override fun favourComic(issueNumber: Int) = setComicFavoured(issueNumber, true)

    private fun setComicFavoured(issueNumber: Int, favoured: Boolean) {
        comicStorageService.getIssue(issueNumber) { comic ->
            comic ?: return@getIssue
            comic.isFavoured = favoured
            comicStorageService.putIssue(comic)
        }
    }
}