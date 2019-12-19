package com.frogobox.hindia.view.adapter

import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.frogobox.hindia.base.view.BaseViewAdapter
import com.frogobox.hindia.base.view.BaseViewHolder
import com.frogobox.hindia.model.Song
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
        private val tv_album = view.tv_album_name_list_item
        private val iv_cover = view.iv_song_image_list_item

        override fun initComponent(data: Song) {
            super.initComponent(data)

            tv_song.text = data.songName
            tv_album.text = data.albumName
            Glide.with(itemView.context).load(data.songImage).into(iv_cover)
        }
    }

}