package com.mdgd.j_commons

import android.app.Application
import com.mdgd.j_commons.core.ComponentProviderImpl
import com.mdgd.j_commons.core.IComponentProvider

/**
 * Created by Max
 * on 30-Apr-17.
 */

class QuakesApp : Application() {

    lateinit var provider: IComponentProvider

    companion object {
        var instance: QuakesApp? = null
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        provider = ComponentProviderImpl(this)
    }
}
