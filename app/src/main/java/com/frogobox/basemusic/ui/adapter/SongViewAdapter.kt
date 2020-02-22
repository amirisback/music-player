package com.frogobox.basemusic.ui.adapter

import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.frogobox.basemusic.base.view.BaseViewAdapter
import com.frogobox.basemusic.base.view.BaseViewHolder
import com.frogobox.basemusic.model.Song
import kotlinx.android.synthetic.main.item_list_song.view.*

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
class SongViewAdapter : BaseViewAdapter<Song>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Song> {
        return SongViewHolder(viewLayout(parent))
    }

    inner class SongViewHolder(view: View) : BaseViewHolder<Song>(view) {

        private val tv_song = view.tv_song_name_list_item

        override fun initComponent(data: Song) {
            super.initComponent(data)

            tv_song.text = data.songName
        }
    }

}