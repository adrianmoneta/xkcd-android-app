package com.moneta.adrian.xkcd.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.moneta.adrian.xkcd.R

class ComicInfoActivity : AppCompatActivity() {

    companion object {

        private const val ISSUE_NUMBER = "ISSUE_NUMBER"

        fun getIntent(context: Context, issueNumber: Int) : Intent {
            val intent = Intent(context, ComicInfoActivity::class.java)
            intent.putExtra(ISSUE_NUMBER, issueNumber)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

    }
}