package com.moneta.adrian.xkcd.mvp

import android.util.Log
import com.moneta.adrian.xkcd.model.Comic
import com.moneta.adrian.xkcd.service.ComicApi
import com.moneta.adrian.xkcd.utils.TAG
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class XKCDModel(private val comicApi: ComicApi) {

    fun getComicsCount(complete: (Int?) -> Unit) {
        comicApi.getLastComicIssue().enqueue(object: Callback<Comic> {

            override fun onResponse(call: Call<Comic>, response: Response<Comic>) {
                Log.d(TAG, "Got [${response.code()}] response code")

                val lastIssue = response.body() ?: return complete(null)
                complete(lastIssue.num)

                //todo cache last issue
            }

            override fun onFailure(call: Call<Comic>, t: Throwable) {
                Log.w(TAG, "Couldn't fetch last issue", t)

                complete(null)
            }
        })
    }

    fun getComic(issueNumber: Int, complete: (Comic?) -> Unit) {
        comicApi.getComic(issueNumber).enqueue(object: Callback<Comic> {

            override fun onResponse(call: Call<Comic>, response: Response<Comic>) = complete(response.body())

            override fun onFailure(call: Call<Comic>, t: Throwable) {
                Log.w(TAG, "Couldn't fetch issue [$issueNumber]", t)
                complete(null)
            }
        })
    }


}