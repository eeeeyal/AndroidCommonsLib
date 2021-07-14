package com.mdgd.j_commons.ui.main.fr.quackes

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mdgd.j_commons.R
import com.mdgd.j_commons.databinding.FragmentRecyclerBinding
import com.mdgd.j_commons.dto.Quake
import com.mdgd.j_commons.recycler.CommonRecyclerAdapter
import com.mdgd.j_commons.recycler_fragment.SwipeRecyclerFragment
import java.util.*

/**
 * Created by Max
 * on 01-May-17.
 */
class EarthQuakesFragment : SwipeRecyclerFragment<QuakesFragmentContract.Presenter, QuakesFragmentContract.Host, Quake>(),
        QuakesFragmentContract.View, View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private var binding: FragmentRecyclerBinding? = null
    private var listener: EndlessScrollListener? = null

    companion object {

        private const val TRANSLATE = -500f

        fun newInstance(): EarthQuakesFragment {
            return EarthQuakesFragment()
        }
    }

    override fun getPresenter(): QuakesFragmentContract.Presenter {
        return QuakesInjector().createPresenter(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recycler, container, false)
        swipe = binding?.swipeRefresh
        recycler = binding?.recycler
        swipe.setOnRefreshListener(this)
        adapter = getAdapter()
        recycler.adapter = adapter
        setHasProgress(true)

        binding?.swipeRefresh?.setColorSchemeColors(Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN)

        listener = object : EndlessScrollListener() {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                binding?.recycler?.postDelayed({ this@EarthQuakesFragment.onLoadMore() }, 200L)
            }
        }
        binding?.recycler?.addOnScrollListener(listener as EndlessScrollListener)

        binding?.toolbarInc?.searchBtn?.setOnClickListener(this)
        binding?.toolbarInc?.extensionToggle?.setOnCheckedChangeListener(this)

        binding?.searchParams?.fromTime?.setOnClickListener(this)
        binding?.searchParams?.fromDate?.setOnClickListener(this)
        binding?.searchParams?.toTime?.setOnClickListener(this)
        binding?.searchParams?.toDate?.setOnClickListener(this)

        return binding?.root
    }

    override fun getAdapter(): CommonRecyclerAdapter<Quake> {
        return EarthQuakesAdapter(activity as Context, this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding?.recycler?.layoutManager = LinearLayoutManager(activity)
        binding?.searchParams?.root?.animate()?.translationYBy(TRANSLATE)?.setDuration(1)?.start()
        binding?.swipeRefresh?.isRefreshing = true
    }

    fun onLoadMore() {
        presenter?.getNextBulk((adapter as EarthQuakesAdapter).lastDate)
    }

    override fun onRefresh() {
        presenter.checkNewEarthQuakes()
    }

    override fun onItemClicked(item: Quake, position: Int) {
        val dialog = QuakeDialog(activity as Context)
        dialog.setQuake(item)
        dialog.show()
    }

    override fun onClick(view: View) {
        val id = view.id
        if (id == R.id.searchBtn) {
//                SearchParams searchData = SearchParams(
//                        binding!!.toolbarInc!!.search.getText().toString(),
//                        binding!!.searchParams!!.fromTime.getText().toString(),
//                        binding!!.searchParams!!.stDate.getText().toString(),
//                        binding!!.searchParams!!.stMag.getText().toString(),
//                        binding!!.searchParams!!.toTime.getText().toString(),
//                        binding!!.searchParams!!.endDate.getText().toString(),
//                        binding!!.searchParams!!.endMag.getText().toString())
//                presenter.searchQuakes(searchData);
        } else if (id == R.id.fromTime) {
            TimePickerDialog(activity, { _: TimePicker, h: Int, m: Int -> binding?.searchParams?.fromTime?.text = String.format(Locale.getDefault(), "%1$2d : %2$2d", h, m) },
                    0, 0, true).show()
        } else if (id == R.id.toTime) {
            TimePickerDialog(activity, { _: TimePicker, h: Int, m: Int -> binding?.searchParams?.toTime?.text = String.format(Locale.getDefault(), "%1$2d : %2$2d", h, m) },
                    0, 0, true).show()
        } else if (id == R.id.fromDate) {
            val calendar = Calendar.getInstance()
            DatePickerDialog(activity!!, { _: DatePicker, y: Int, m: Int, d: Int -> binding?.searchParams?.fromDate?.text = String.format(Locale.getDefault(), "%1$2d.%2$2d.%3$4d", d, m, y) },
                    calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        } else if (id == R.id.toDate) {
            val calendar = Calendar.getInstance()
            DatePickerDialog(activity!!, { _: DatePicker, y: Int, m: Int, d: Int -> binding?.searchParams?.toDate?.text = String.format(Locale.getDefault(), "%1$2d.%2$2d.%3$4d", d, m, y) },
                    calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    override fun onCheckedChanged(compoundButton: CompoundButton, b: Boolean) {
        val translateDist = if (b) -1 * TRANSLATE else TRANSLATE
        binding?.searchParams?.root?.animate()?.translationYBy(translateDist)?.setDuration(300)?.start()
    }

    override fun setState(state: State) {
        if(state.showError) showToast(R.string.shit, state.errorMessage)

        if(state.showProgress) showProgress(R.string.empty, R.string.wait_please)
        else hideProgress()

        if (state.updateData) {
            binding?.toolbarInc?.toolbarIcon?.requestFocus()
            if (state.isFirstPage) {
                listener?.resetState()
                adapter?.setItems(state.data)
            } else adapter?.addItems(state.data)
        }
    }
}
