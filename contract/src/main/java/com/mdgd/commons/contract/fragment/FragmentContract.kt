package com.mdgd.commons.contract.fragment

import com.mdgd.commons.contract.ToastDecor
import com.mdgd.commons.contract.mvp.MvpContract
import com.mdgd.commons.contract.progress.ProgressContainer

/**
 * Created by Max
 * on 05/09/2018.
 */
class FragmentContract {

    interface Host : ProgressContainer, ToastDecor {

        val isFinishing: Boolean

        fun finish()

        fun onBackPressed()
    }

    interface Presenter<V : View> : MvpContract.Presenter<V>

    interface View : MvpContract.View
}
