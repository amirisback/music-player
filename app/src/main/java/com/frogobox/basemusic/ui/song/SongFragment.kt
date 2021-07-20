package com.frogobox.basemusic.ui.song


import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.frogobox.basemusic.R
import com.frogobox.basemusic.core.BaseFragment
import com.frogobox.basemusic.databinding.FragmentSongBinding
import com.frogobox.basemusic.model.Song
import com.frogobox.basemusic.util.ConstHelper.Ext.DEF_RAW
import com.frogobox.basemusic.util.ConstHelper.Extra.EXTRA_SONG
import com.frogobox.basemusic.util.RawDataHelper
import com.frogobox.recycler.core.FrogoRecyclerViewListener

/**
 * A simple [Fragment] subclass.
 */
class SongFragment : BaseFragment<FragmentSongBinding>() {

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup
    ): FragmentSongBinding {
        return FragmentSongBinding.inflate(inflater, container, false)
    }

    override fun setupViewModel() {
    }

    override fun setupUI(savedInstanceState: Bundle?) {
        setupRecyclerView()
        setupShowAdsInterstitial()
    }

    private fun arrayString(): MutableList<String> {
        return RawDataHelper()
            .fetchDataShuffle(context, R.raw._asset_song_files)
    }

    private fun resString(value: String, type: String): Int {
        return resources.getIdentifier(value, type, context?.packageName)
    }

    private fun arraySongData(): MutableList<Song> {
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
        adapter.setupRequirement(R.layout.item_list_song, arraySongData(),
            object : FrogoRecyclerViewListener<Song> {
                override fun onItemClicked(data: Song) {
                    baseStartActivity<SongPlayingActivity, Song>(EXTRA_SONG, data)
                    setupShowAdsInterstitial()
                }

                override fun onItemLongClicked(data: Song) {
                }
            })
        return adapter
    }

    private fun setupRecyclerView() {
        binding?.apply {
            recyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = setupAdapter()
        }
    }

}