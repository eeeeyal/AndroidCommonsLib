package com.mdgd.commons.contract.activity

import com.mdgd.commons.contract.mvp.MvpContract

/**
 * Created by Max
 * on 05/09/2018.
 */
class ActivityContract {

    interface IPresenter<T : View> : MvpContract.Presenter<T>

    interface View : MvpContract.View {

        val isFinishing: Boolean

        val packageName: String

        fun getString(id: Int): String

        fun getString(id: Int, vararg args: Any): String

        fun finish()

        fun onBackPressed()
    }
}
