package com.mdgd.commons.contract

/**
 * Created by Max
 * on 23/07/2018.
 */
interface ToastDecor {

    fun showToast(msgRes: Int)

    fun showToast(msgRes: Int, vararg args: Any)
}
