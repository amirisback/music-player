package com.frogobox.hindia.ui.fragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager

import com.frogobox.hindia.R
import com.frogobox.hindia.base.ui.BaseFragment
import com.frogobox.hindia.base.view.BaseViewListener
import com.frogobox.hindia.ui.activity.FanartDetailActivity
import com.frogobox.hindia.util.helper.ConstHelper.Extra.EXTRA_FANART
import com.frogobox.hindia.util.helper.RawDataHelper
import com.frogobox.hindia.view.adapter.FanartViewAdapter
import kotlinx.android.synthetic.main.fragment_fanart.*

/**
 * A simple [Fragment] subclass.
 */
class FanartFragment : BaseFragment(), BaseViewListener<String> {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fanart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupShowAdsInterstitial()
    }

    private fun arrayFanArt(): MutableList<String> {
        return RawDataHelper().fetchData(context, R.raw._asset_image_fanart)
    }

    private fun setupAdapter(): FanartViewAdapter {
        val adapter = FanartViewAdapter()
        adapter.setupRequirement(this, arrayFanArt(), R.layout.item_grid_fanart)
        return adapter
    }

    private fun setupRecyclerView() {
        recycler_view.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recycler_view.adapter = setupAdapter()
    }

    override fun onItemClicked(data: String) {
        startActivity(
            Intent(context, FanartDetailActivity::class.java).putExtra(
                EXTRA_FANART,
                data
            )
        )
    }

    override fun onItemLongClicked(data: String) {

    }


}
