package com.frogobox.basemusicplayer.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.frogobox.basemusicplayer.R
import com.frogobox.basemusicplayer.base.ui.BaseFragment
import com.frogobox.basemusicplayer.base.view.BaseViewListener
import com.frogobox.basemusicplayer.model.Song
import com.frogobox.basemusicplayer.ui.activity.SongPlayingActivity
import com.frogobox.basemusicplayer.util.helper.ConstHelper.Ext.DEF_DRAWABLE
import com.frogobox.basemusicplayer.util.helper.ConstHelper.Ext.DEF_RAW
import com.frogobox.basemusicplayer.util.helper.ConstHelper.Extra.EXTRA_SONG
import com.frogobox.basemusicplayer.util.helper.RawDataHelper
import com.frogobox.basemusicplayer.view.adapter.SongViewAdapter
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
        return RawDataHelper().fetchData(context, R.raw._asset_song_files)
    }

    private fun resString(value: String, type: String) : Int {
        return resources.getIdentifier(value, type, context?.packageName)
    }

    private fun arraySongData() : MutableList<Song> {
        val arraySong = mutableListOf<Song>()
        for (i in arrayString()) {
            val splitString = i.split(";")

            val songCover = resString(splitString[0], DEF_DRAWABLE)
            val songMusic = resString(splitString[1], DEF_RAW)
            val songName = splitString[2]
            val artistName = splitString[3]
            val albumName = splitString[4]

            val song = Song(songCover, songMusic, songName, artistName, albumName)
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
    }

    override fun onItemLongClicked(data: Song) {

    }


}
