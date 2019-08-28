package com.moneta.adrian.xkcd.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.moneta.adrian.xkcd.model.Comic

class ComicInfoAdapter(private val comic: Comic): BaseAdapter() {

    private val items = listOf<String>(
        "title: ${comic.title}",
        "issue: #${comic.num}",
        "date:  "
    )

    override fun getView(pos: Int, convertView: View?, parent: ViewGroup?): View {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItem(pos: Int): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemId(pos: Int): Long = pos.toLong()

    override fun getCount(): Int = 0

}