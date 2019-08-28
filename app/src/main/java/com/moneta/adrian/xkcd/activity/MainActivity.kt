package com.moneta.adrian.xkcd.activity

import android.database.Cursor
import android.os.Bundle
import android.util.Log
import android.view.View
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
    private var loaderId = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter.view = this
        presenter.load()

        setContentView(R.layout.activity_main)
        initControls()
        title = "Loading..."
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.view = null
    }


    //XKCDView
    override fun onSelectedIssueChanged() = loadCurrentIssue()

    override fun onLoading() {
        img_placeholder.visibility = View.GONE
        progress.visibility = View.VISIBLE
    }


    //LoaderManager.LoaderCallbacks
    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        val uri = UriHelper.buildComicUri(presenter.currentIssue)
        return CursorLoader(this, uri, null, null, null, null)

    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        data ?: return
        if(!data.moveToFirst()) return
        val comic = CursorHelper.readComic(data)
        comic.imgBitmap ?: return presenter.requestBitmap(comic)
        img_placeholder.setImageBitmap(comic.imgBitmap)
        img_placeholder.visibility = View.VISIBLE
        progress.visibility = View.GONE
    }

    override fun onLoaderReset(loader: Loader<Cursor>) { }




    private fun loadCurrentIssue() {
        title = "#${presenter.currentIssue}"
        LoaderManager.getInstance(this).initLoader(loaderId++, null, this)
    }

    private fun initControls() {
        btn_first.setOnClickListener { presenter.selectFirst() }
        btn_previous.setOnClickListener { presenter.selectPrevious() }
        btn_random.setOnClickListener { presenter.selectRandom() }
        btn_next.setOnClickListener { presenter.selectNext() }
        btn_last.setOnClickListener { presenter.selectLast() }
    }


}
