package com.moneta.adrian.xkcd.service

import com.moneta.adrian.xkcd.model.Comic

interface StorageService {
    fun putIssue(comic: Comic)
}