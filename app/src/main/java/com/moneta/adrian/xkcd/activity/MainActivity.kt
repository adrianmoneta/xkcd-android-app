package com.moneta.adrian.xkcd.activity

import android.database.Cursor
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import com.moneta.adrian.xkcd.R
import com.moneta.adrian.xkcd.mvp.XKCDPresenter
import com.moneta.adrian.xkcd.mvp.XKCDView
import com.moneta.adrian.xkcd.provider.CursorHelper
import com.moneta.adrian.xkcd.provider.UriHelper
import com.moneta.adrian.xkcd.utils.TAG
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity(), XKCDView, LoaderManager.LoaderCallbacks<Cursor> {



    private val presenter: XKCDPresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter.view = this
        presenter.load()

        setContentView(R.layout.activity_main)
    }


    //XKCDView
    override fun onCount(count: Int) = load(count)


    //LoaderManager.LoaderCallbacks
    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        val uri = UriHelper.buildComicUri(id)
        return CursorLoader(this, uri, null, null, null, null)

    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        data ?: return
        if(data.moveToFirst()) {
            testApiTextView.text = CursorHelper.readComic(data).num.toString()
        }
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        Log.i(TAG, "loading")
    }


    private fun load(issueNumber: Int) {
        LoaderManager.getInstance(this).initLoader(issueNumber, null, this)
    }



}
