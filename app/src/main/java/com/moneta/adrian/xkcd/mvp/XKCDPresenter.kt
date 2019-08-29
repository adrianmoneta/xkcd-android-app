package com.moneta.adrian.xkcd.mvp

import com.moneta.adrian.xkcd.model.Comic
import java.lang.ref.WeakReference

class XKCDPresenter(val model : XKCDModel) {

    private var _view : WeakReference<XKCDView>? = null

    //data
    var issuesCount = 0
    var currentIssue = -1

    var view : XKCDView?
        get() = _view?.get()
        set(value) {
            _view = if(null != value) WeakReference(value) else null
        }

    fun load() {
        view ?: return
        model.getLastIssue { lastIssue ->
            lastIssue ?: return@getLastIssue load()
            issuesCount = lastIssue.num
            if(-1 != currentIssue) return@getLastIssue
            currentIssue = issuesCount
            view?.onSelectedIssueChanged()
        }
    }

    fun requestBitmapUpdate(comic: Comic) {
        view ?: return
        model.requestBitmap(comic) { success ->
            if(success) return@requestBitmap
            if(currentIssue == comic.num) requestBitmapUpdate(comic)
        }
    }

    fun favourCurrentComic() = model.favourComic(currentIssue)

    fun unfavourCurrentComic() = model.unfavourComic(currentIssue)


    fun selectFirst() {
        currentIssue = 1
        view?.onLoading()
        view?.onSelectedIssueChanged()
        model.requestComic(currentIssue) {}
    }

    fun selectPrevious() {
        if(currentIssue <= 1) return
        currentIssue--
        view?.onLoading()
        view?.onSelectedIssueChanged()
        model.requestComic(currentIssue) {}
    }

    fun selectRandom() {
        currentIssue = (1..issuesCount).random()
        view?.onLoading()
        view?.onSelectedIssueChanged()
        model.requestComic(currentIssue) {}
    }

    fun selectNext() {
        if(currentIssue == issuesCount) return
        currentIssue++
        view?.onLoading()
        view?.onSelectedIssueChanged()
        model.requestComic(currentIssue) {}

    }

    fun selectLast() {
        currentIssue = issuesCount
        view?.onLoading()
        view?.onSelectedIssueChanged()
        model.requestComic(currentIssue) {}
    }


}