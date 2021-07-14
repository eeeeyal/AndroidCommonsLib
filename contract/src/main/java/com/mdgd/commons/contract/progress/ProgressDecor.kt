package com.mdgd.commons.contract.progress

/**
 * Created by Max
 * on 05/09/2018.
 */
open interface ProgressDecor {

    val isShowing: Boolean

    fun show()

    fun dismiss()
}
