package com.mdgd.j_commons.ui.main.fr.quackes

import com.mdgd.commons.contract.fragment.FragmentContract
import com.mdgd.j_commons.dto.SearchParams
import java.util.*

/**
 * Created by max
 * on 2/2/18.
 */

class QuakesFragmentContract {

    interface View : FragmentContract.View {
        fun setState(state: State)
    }

    interface Host : FragmentContract.Host

    interface Presenter : FragmentContract.Presenter<View> {
        fun searchQuakes(searchParams: SearchParams?)

        fun checkNewEarthQuakes()

        fun getNextBulk(lastDate: Date)
    }
}
