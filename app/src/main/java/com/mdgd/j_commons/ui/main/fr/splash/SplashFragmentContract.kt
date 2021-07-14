package com.mdgd.j_commons.ui.main.fr.splash

import com.mdgd.commons.contract.fragment.FragmentContract

/**
 * Created by max
 * on 2/2/18.
 */

class SplashFragmentContract {

    interface View : FragmentContract.View

    interface Host : FragmentContract.Host

    interface Presenter : FragmentContract.Presenter<View>
}
