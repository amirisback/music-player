package com.frogobox.basemusic.ui.songlyric

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.frogobox.basemusic.R
import com.frogobox.basemusic.model.Song
import com.frogobox.recycler.core.FrogoRecyclerViewAdapter
import com.frogobox.recycler.core.FrogoRecyclerViewHolder

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * BaseMusicPlayer
 * Copyright (C) 19/12/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.basemusicplayer.view.adapter
 *
 */
class SongLyricViewAdapter : FrogoRecyclerViewAdapter<Song>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FrogoRecyclerViewHolder<Song> {
        return SongViewHolder(viewLayout(parent))
    }

    inner class SongViewHolder(view: View) : FrogoRecyclerViewHolder<Song>(view) {

        private val tv_song = view.findViewById<TextView>(R.id.tv_song_name_list_item)

        override fun initComponent(data: Song) {
            tv_song.text = data.songName
        }
    }

}