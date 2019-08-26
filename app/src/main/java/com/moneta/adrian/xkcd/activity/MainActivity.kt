package com.moneta.adrian.xkcd.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.moneta.adrian.xkcd.R
import com.moneta.adrian.xkcd.model.Comic
import com.moneta.adrian.xkcd.mvp.XKCDPresenter
import com.moneta.adrian.xkcd.mvp.XKCDView
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity(), XKCDView {


    private val presenter: XKCDPresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter.view = this

        setContentView(R.layout.activity_main)
    }


    //XKCDView
    override fun onCount(count: Int) {
        testApiTextView.text = "$count"
    }

    override fun onCountError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onComic(comic: Comic) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onComicError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
