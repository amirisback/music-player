package com.frogobox.basemusic.ui.song


import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.frogobox.basemusic.R
import com.frogobox.basemusic.core.BaseFragment
import com.frogobox.basemusic.databinding.FragmentSongBinding
import com.frogobox.basemusic.databinding.ItemSongBinding
import com.frogobox.basemusic.model.Song
import com.frogobox.basemusic.util.ConstHelper.Ext.DEF_DRAWABLE
import com.frogobox.basemusic.util.ConstHelper.Ext.DEF_RAW
import com.frogobox.basemusic.util.ConstHelper.Extra.EXTRA_SONG
import com.frogobox.basemusic.util.RawDataHelper
import com.frogobox.recycler.core.IFrogoBindingAdapter

/**
 * A simple [Fragment] subclass.
 */
class SongFragment : BaseFragment<FragmentSongBinding>() {

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup
    ): FragmentSongBinding {
        return FragmentSongBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
    }

    override fun setupUI(savedInstanceState: Bundle?) {
        setupRecyclerView()
    }

    private fun arrayString(): MutableList<String> {
        return RawDataHelper().fetchData(context, R.raw._asset_song_files)
    }

    private fun resString(value: String, type: String): Int {
        return resources.getIdentifier(value, type, context?.packageName)
    }

    private fun arraySongData(): MutableList<Song> {
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

    private fun setupRecyclerView() {

        val callback = object : IFrogoBindingAdapter<Song, ItemSongBinding> {
            override fun onItemClicked(data: Song) {
                baseStartActivity<SongPlayingActivity, Song>(EXTRA_SONG, data)
            }

            override fun onItemLongClicked(data: Song) {
            }

            override fun setViewBinding(parent: ViewGroup): ItemSongBinding {
                return ItemSongBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            }

            override fun setupInitComponent(binding: ItemSongBinding, data: Song) {
                binding.apply {
                    tvSongNameListItem.text = data.songName
                    tvAlbumNameListItem.text = data.albumName
                    Glide.with(root.context).load(data.songImage).into(ivSongImageListItem)
                }
            }
        }

        binding?.recyclerView?.injectorBinding<Song, ItemSongBinding>()
            ?.addData(arraySongData())
            ?.addCallback(callback)
            ?.createLayoutLinearVertical(false)
            ?.build()

    }

}
