package com.mdgd.commons.contract.mvp

import androidx.lifecycle.LifecycleObserver
import com.mdgd.commons.contract.ToastDecor
import com.mdgd.commons.contract.progress.ProgressContainer

class MvpContract {

    interface Presenter<T : View>: LifecycleObserver {
        fun subscribe(view: T)

        fun unsubscribe()
    }

    interface View : ProgressContainer, ToastDecor
}
