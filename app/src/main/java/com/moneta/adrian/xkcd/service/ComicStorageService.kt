package com.moneta.adrian.xkcd.service

import com.moneta.adrian.xkcd.model.Comic

interface ComicStorageService {
    fun putIssue(comic: Comic)
    fun getIssue(issueNumber: Int, complete: (Comic?) -> Unit)
    fun getLastIssue(complete: (Comic?) -> Unit)
}