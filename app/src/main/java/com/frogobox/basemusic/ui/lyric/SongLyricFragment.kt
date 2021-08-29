package com.frogobox.basemusic.ui.lyric


import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.frogobox.basemusic.R
import com.frogobox.basemusic.core.BaseFragment
import com.frogobox.basemusic.databinding.FragmentSongLyricBinding
import com.frogobox.basemusic.model.SongLyric
import com.frogobox.basemusic.util.ConstHelper.Ext.DEF_RAW
import com.frogobox.basemusic.util.ConstHelper.Extra.EXTRA_SONG
import com.frogobox.recycler.core.FrogoRecyclerViewListener
import com.frogobox.sdk.core.FrogoFunc.fetchData

/**
 * A simple [Fragment] subclass.
 */
class SongLyricFragment : BaseFragment<FragmentSongLyricBinding>() {

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup
    ): FragmentSongLyricBinding {
        return FragmentSongLyricBinding.inflate(inflater, container, false)
    }

    override fun setupViewModel() {
    }

    override fun setupUI(savedInstanceState: Bundle?) {
        setupRecyclerView()
    }

    private fun arrayString(): MutableList<String> {
        return fetchData(requireContext(), R.raw._asset_song_lyric_files)
    }

    private fun resString(value: String, type: String): Int {
        return resources.getIdentifier(value, type, context?.packageName)
    }

    private fun arraySongData(): MutableList<SongLyric> {
        val arraySong = mutableListOf<SongLyric>()
        for (i in arrayString()) {
            val splitString = i.split(";")

            val songMusic = resString(splitString[0], DEF_RAW)
            val songName = splitString[1]
            val lyrics = resString(splitString[2], DEF_RAW)

            val song = SongLyric(songMusic, songName, lyrics)
            arraySong.add(song)

        }
        return arraySong
    }

    private fun setupAdapter(): SongLyricViewAdapter {
        val adapter = SongLyricViewAdapter()
        adapter.setupRequirement(R.layout.item_song_lyric, arraySongData(),
            object : FrogoRecyclerViewListener<SongLyric> {
                override fun onItemClicked(data: SongLyric) {
                    baseStartActivity<SongLyricPlayingActivity, SongLyric>(EXTRA_SONG, data)
                    setupShowAdsInterstitial()
                }

                override fun onItemLongClicked(data: SongLyric) {
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