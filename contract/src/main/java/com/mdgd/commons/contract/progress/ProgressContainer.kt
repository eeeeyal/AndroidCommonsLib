package com.mdgd.commons.contract.progress

/**
 * Created by Max
 * on 23/07/2018.
 */
interface ProgressContainer {

    fun hasProgress(): Boolean

    fun showProgress(title: String, message: String)

    fun showProgress(titleRes: Int, messageRes: Int)

    fun showProgress()

    fun hideProgress()
}
