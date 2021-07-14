package com.mdgd.j_commons.ui.main

import androidx.fragment.app.Fragment
import com.mdgd.j_commons.fragment.HostActivity
import com.mdgd.j_commons.ui.main.fr.quackes.EarthQuakesFragment
import com.mdgd.j_commons.ui.main.fr.splash.SplashFragment

/**
 * Created by Max
 * on 01-May-17.
 */

class MainActivity : HostActivity<MainScreenContract.Presenter>(), MainScreenContract.View {

    override fun getPresenter(): MainScreenContract.Presenter {
        return MainInjector().createPresenter(this)
    }

    override fun getFirstFragment(): Fragment {
        container.postDelayed({ proceedFromSplash() }, 600L)
        return SplashFragment.newInstance()
    }

    private fun proceedFromSplash() {
        replaceFragment(EarthQuakesFragment.newInstance())
    }

//    override fun onRetainCustomNonConfigurationInstance(): Any? {
//        return super.onRetainCustomNonConfigurationInstance()
//    }
//
//    override fun getLastNonConfigurationInstance(): Any? {
//        return super.getLastNonConfigurationInstance()
//    }
}
