package com.mdgd.commons.contract.mvp

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

open class BasicPresenter<T : MvpContract.View> : MvpContract.Presenter<T> {
    protected var view: T? = null

    override fun subscribe(view: T) {
        this.view = view
    }

    override fun unsubscribe() {
        view = null
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    open fun onAny(source: LifecycleOwner, event: Lifecycle.Event) {

    }
}
