package com.moneta.adrian.xkcd

import android.app.Application
import com.moneta.adrian.xkcd.mvp.XKCDModel
import com.moneta.adrian.xkcd.mvp.XKCDPresenter
import com.moneta.adrian.xkcd.service.ApiFactory
import com.moneta.adrian.xkcd.service.ComicApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

class XKCDApplication : Application() {

    private val koinModules = module {
                //services
        single { ApiFactory.getApi(ComicApi::class.java) }

        //models
        single { XKCDModel(get()) }

        //presenters
        single { XKCDPresenter(get()) }
    }

     override fun onCreate() {
         super.onCreate()
         initInjector()
     }

     private fun initInjector() {
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@XKCDApplication)
            modules(koinModules)
        }
     }


}