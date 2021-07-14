package com.mdgd.j_commons.ui.main.fr.quake

import com.mdgd.j_commons.core.repo.IRepo
import com.mdgd.j_commons.fragment.FragmentPresenter

class QuakeFragmentPresenter(private val repo: IRepo) :
        FragmentPresenter<QuakeFragmentContract.View>(), QuakeFragmentContract.Presenter {

}
