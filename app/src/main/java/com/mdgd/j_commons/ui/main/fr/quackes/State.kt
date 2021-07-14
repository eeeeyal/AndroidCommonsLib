package com.mdgd.j_commons.ui.main.fr.quackes

import com.mdgd.j_commons.dto.Quake

class State(val showProgress: Boolean, val showError: Boolean, val errorMessage: String?, val isFirstPage: Boolean, val updateData: Boolean, val data: List<Quake>) {
    companion object {

        fun showProgressState(showProgress: Boolean): State {
            return State(showProgress, false, "", false, false, ArrayList(0))
        }

        fun showErrorState(showError: Boolean, errorMessage: String?): State {
            return State(false, showError, errorMessage, false, false, ArrayList(0))
        }

        fun newDataState(page: Int, data: List<Quake>): State {
            return State(false, false, "", 1 == page, true, data)
        }
    }
}