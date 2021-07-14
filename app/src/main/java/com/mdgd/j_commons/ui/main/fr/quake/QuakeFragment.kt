package com.mdgd.j_commons.ui.main.fr.quake

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.mdgd.j_commons.R
import com.mdgd.j_commons.databinding.FragmentRecyclerBinding
import com.mdgd.j_commons.fragment.HostedFragment

/**
 * Created by Max
 * on 01-May-17.
 */
class QuakeFragment : HostedFragment<QuakeFragmentContract.Presenter, QuakeFragmentContract.Host>(),
        QuakeFragmentContract.View {

    private var binding: FragmentRecyclerBinding? = null

    companion object {

        fun newInstance(): QuakeFragment {
            return QuakeFragment()
        }
    }

    override fun getPresenter(): QuakeFragmentContract.Presenter {
        return QuakeInjector().createPresenter(this)
    }

    override fun getLayoutResId(): Int = R.layout.fragment_quake

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutResId(), container, false)
        return binding?.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // todo get data
    }
}
