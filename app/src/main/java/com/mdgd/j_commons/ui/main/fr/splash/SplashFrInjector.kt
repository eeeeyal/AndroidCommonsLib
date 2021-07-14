package com.mdgd.j_commons.ui.main.fr.splash

import com.mdgd.commons.injection.IInjector
import com.mdgd.j_commons.QuakesApp
import com.mdgd.j_commons.core.IComponentProvider

/**
 * Created by Owner
 * on 13/05/2019.
 */
class SplashFrInjector : IInjector<SplashFragmentContract.Presenter, SplashFragmentContract.View> {

    private val provider: IComponentProvider = QuakesApp.instance!!.provider

    override fun createPresenter(view: SplashFragmentContract.View): SplashFragmentContract.Presenter {
        return SplashFragmentPresenter()
    }
}
