package com.moneta.adrian.xkcd.service

import com.moneta.adrian.xkcd.model.Comic
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ComicApi {

    @GET("{issueNumber}/info.0.json")
    fun getComic(@Path("issueNumber") issueNumber: Int) : Call<Comic>

    @GET("info.0.json")
    fun getLastComicIssue(): Call<Comic>

}