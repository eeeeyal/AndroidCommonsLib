package com.mdgd.j_commons.ui.main.fr.quackes

import com.mdgd.commons.result.ICallback
import com.mdgd.j_commons.core.repo.IRepo
import com.mdgd.j_commons.dto.SearchParams
import com.mdgd.j_commons.fragment.FragmentPresenter
import java.util.*

class QuakesFragmentPresenter(private val repo: IRepo) :
        FragmentPresenter<QuakesFragmentContract.View>(), QuakesFragmentContract.Presenter {
    private var currentPage: Int = 0

    override fun searchQuakes(searchParams: SearchParams?) {
        if (searchParams == null) {
            checkNewEarthQuakes()
            return
        }
        view.setState(State.showProgressState(true))
        repo.searchQuakes(searchParams, ICallback {
            if (it.isFail) {
                view.setState(State.showErrorState(true, it.error?.message))
                currentPage--
            } else view.setState(State.newDataState(currentPage, it.data!!))
        })
    }

    override fun subscribe(view: QuakesFragmentContract.View?) {
        super.subscribe(view)
        checkNewEarthQuakes()
    }

    override fun checkNewEarthQuakes() {
        currentPage = 1
        view.setState(State.showProgressState(true))
        repo.searchQuakes(SearchParams("", repo.getPrefs().lastUpdateDate), ICallback {
            if (it.isFail) {
                view.setState(State.showErrorState(true, it.error?.message))
                currentPage--
            } else view.setState(State.newDataState(currentPage, it.data!!))
        })
    }

    override fun getNextBulk(lastDate: Date) {
        currentPage++
        // add check is there more data in other cases
        view.setState(State.showProgressState(true))
        repo.getEarthquakesBeforeDate(SearchParams("", SearchParams.DEF_TIME, "", lastDate.time), ICallback {
            if (it.isFail) {
                view.setState(State.showErrorState(true, it.error?.message))
                currentPage--
            } else view.setState(State.newDataState(currentPage, it.data!!))
        })
    }
}
