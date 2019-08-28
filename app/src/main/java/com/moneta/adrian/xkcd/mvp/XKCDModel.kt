package com.moneta.adrian.xkcd.mvp

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import com.moneta.adrian.xkcd.model.Comic
import com.moneta.adrian.xkcd.service.ComicApi
import com.moneta.adrian.xkcd.service.ComicStorageService
import com.moneta.adrian.xkcd.service.FavouritesService
import com.moneta.adrian.xkcd.utils.TAG
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


class XKCDModel(
    private val comicApi: ComicApi,
    private val comicStorageService: ComicStorageService,
    private val favouritesService: FavouritesService) {


    fun getLastIssue(complete: (Comic?) -> Unit) {
        comicApi.getLastComicIssue().enqueue(object: Callback<Comic> {

            override fun onResponse(call: Call<Comic>, response: Response<Comic>) {
                val lastIssue = response.body() ?: return complete(null)
                comicStorageService.getIssue(lastIssue.num) { cached ->
                    if(null == cached) comicStorageService.putIssue(lastIssue)
                    complete(lastIssue)
                }
            }

            override fun onFailure(call: Call<Comic>, t: Throwable) {
                Log.w(TAG, "Couldn't fetch last issue", t)

                complete(null)
            }
        })
    }

    fun requestComic(issueNumber: Int, complete: (Boolean) -> Unit) {
        comicStorageService.getIssue(issueNumber) { cachedIssue ->

            if(cachedIssue != null) return@getIssue complete(true)

            comicApi.getComic(issueNumber).enqueue(object: Callback<Comic> {

                override fun onResponse(call: Call<Comic>, response: Response<Comic>) {
                    val issue = response.body() ?: return complete(false)
                    comicStorageService.putIssue(issue)
                    complete(true)
                }

                override fun onFailure(call: Call<Comic>, t: Throwable) {
                    Log.w(TAG, "Couldn't fetch issue number [$issueNumber]", t)
                    complete(false)
                }

            })

        }

    }

    fun requestBitmap(comic: Comic, complete: ((Boolean) -> Unit)) {
        Picasso.get().load(comic.img).into(object: Target {
            override fun onPrepareLoad(placeHolderDrawable: Drawable?) { }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                complete(false)
            }

            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                bitmap ?: return complete(false)
                comic.imgBitmap = bitmap
                comicStorageService.putIssue(comic)
                complete(true)
            }
        })
    }

    fun favourComic(issueNumber: Int) = favouritesService.favourComic(issueNumber)

    fun unfavourComic(issueNumber: Int) = favouritesService.unfavourComic(issueNumber)

}