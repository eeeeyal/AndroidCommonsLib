package com.mdgd.j_commons.ui.main

import androidx.lifecycle.LifecycleObserver
import com.mdgd.commons.contract.activity.ActivityContract


/**
 * Created by max
 * on 2/2/18.
 */

class MainScreenContract {

    interface Presenter : ActivityContract.IPresenter<View>, LifecycleObserver

    interface View : ActivityContract.View
}
