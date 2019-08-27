package com.moneta.adrian.xkcd.mvp

import java.lang.ref.WeakReference

class XKCDPresenter(val model : XKCDModel) {

    private var _view : WeakReference<XKCDView>? = null


    var view : XKCDView?
        get() = _view?.get()
        set(value) {
            _view = if(null != value) WeakReference(value) else null
        }

    fun load() {
        view ?: return
        model.getComicsCount { count ->
            //todo display info about retrying
            count ?: return@getComicsCount load()
            view?.onCount(count)
        }
    }
}