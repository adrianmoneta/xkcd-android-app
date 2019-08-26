package com.moneta.adrian.xkcd.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.moneta.adrian.xkcd.R
import com.moneta.adrian.xkcd.model.Comic
import com.moneta.adrian.xkcd.service.ApiFactory
import com.moneta.adrian.xkcd.service.ComicApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.moneta.adrian.xkcd.utils.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        testApi()

    }

    fun testApi() {
        val api = ApiFactory.getApi(ComicApi::class.java)
        api.getComic(10).enqueue(object: Callback<Comic> {

            override fun onResponse(call: Call<Comic>, response: Response<Comic>) {
                Log.d(TAG, "Got [${response.code()}] response code")
                testApiTextView.text = response.body()?.toString() ?: "Almost"
                Log.d(TAG, response.body()?.toString() ?: "")
            }

            override fun onFailure(call: Call<Comic>, t: Throwable) {
                Log.w(TAG, "Couldn't resolve comic", t)
            }


        })
    }
}
