package com.mdgd.j_commons.ui.main.fr.splash

import com.mdgd.j_commons.R
import com.mdgd.j_commons.fragment.HostedFragment

/**
 * Created by Max
 * on 01-May-17.
 */

class SplashFragment : HostedFragment<SplashFragmentContract.Presenter, SplashFragmentContract.Host>(),
        SplashFragmentContract.View {

    companion object {

        fun newInstance(): SplashFragment {
            return SplashFragment()
        }
    }

    override fun getPresenter(): SplashFragmentContract.Presenter {
        return SplashFrInjector().createPresenter(this)
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_splash
    }
}
