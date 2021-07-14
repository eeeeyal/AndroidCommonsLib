package com.mdgd.j_commons.ui.main.fr.quake

import com.mdgd.commons.contract.fragment.FragmentContract

/**
 * Created by max
 * on 2/2/18.
 */

class QuakeFragmentContract {

    interface View : FragmentContract.View {
    }

    interface Host : FragmentContract.Host

    interface Presenter : FragmentContract.Presenter<View> {

    }
}
