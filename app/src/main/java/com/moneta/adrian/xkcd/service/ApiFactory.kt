package com.moneta.adrian.xkcd.service

import android.util.Log
import com.moneta.adrian.xkcd.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

    private const val BASE_URL = "https://xkcd.com/"

    private var retrofitBuilder: Retrofit.Builder


    init {
        retrofitBuilder = Retrofit.Builder()
            .client(buildOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
    }


    private fun buildOkHttpClient() : OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        if(BuildConfig.DEBUG) {
            val loggingInterceptor = Interceptor { chain ->
                Log.d(chain.request().method, chain.request().url.toString())
                chain.proceed(chain.request())
            }
            clientBuilder.addInterceptor(loggingInterceptor)
        }
        return clientBuilder.build()
    }

    fun <T> getApi(apiClass: Class<T>): T = retrofitBuilder.build().create(apiClass)
}