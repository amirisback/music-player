package com.frogobox.basemusic.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.frogobox.basemusic.R
import com.frogobox.basemusic.base.ui.BaseFragment
import com.frogobox.basemusic.base.view.BaseViewListener
import com.frogobox.basemusic.model.Song
import com.frogobox.basemusic.ui.activity.SongPlayingActivity
import com.frogobox.basemusic.util.ConstHelper.Ext.DEF_DRAWABLE
import com.frogobox.basemusic.util.ConstHelper.Ext.DEF_RAW
import com.frogobox.basemusic.util.ConstHelper.Extra.EXTRA_SONG
import com.frogobox.basemusic.util.RawDataHelper
import com.frogobox.basemusic.ui.adapter.SongViewAdapter
import kotlinx.android.synthetic.main.fragment_song.*


/**
 * A simple [Fragment] subclass.
 */
class SongFragment : BaseFragment(), BaseViewListener<Song> {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_song, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupShowAdsInterstitial()
    }

    private fun arrayString(): MutableList<String> {
        return RawDataHelper()
            .fetchDataShuffle(context, R.raw._asset_song_files)
    }

    private fun resString(value: String, type: String) : Int {
        return resources.getIdentifier(value, type, context?.packageName)
    }

    private fun arraySongData() : MutableList<Song> {
        val arraySong = mutableListOf<Song>()
        for (i in arrayString()) {
            val splitString = i.split(";")

            val songMusic = resString(splitString[0], DEF_RAW)
            val songName = splitString[1]
            val lyrics = resString(splitString[2], DEF_RAW)

            val song = Song(songMusic, songName, lyrics)
            arraySong.add(song)

        }
        return arraySong
    }

    private fun setupAdapter(): SongViewAdapter {
        val adapter = SongViewAdapter()
        adapter.setupRequirement(this, arraySongData(), R.layout.item_list_song)
        return adapter
    }

    private fun setupRecyclerView() {
        recycler_view.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recycler_view.adapter = setupAdapter()
    }

    override fun onItemClicked(data: Song) {
        baseStartActivity<SongPlayingActivity, Song>(EXTRA_SONG, data)
        setupShowAdsInterstitial()
    }

    override fun onItemLongClicked(data: Song) {

    }


}
