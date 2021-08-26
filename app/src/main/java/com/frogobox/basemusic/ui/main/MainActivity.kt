package com.frogobox.basemusic.ui.main

import android.os.Bundle
import com.frogobox.basemusic.core.BaseActivity
import com.frogobox.basemusic.databinding.ActivityMainBinding
import com.frogobox.basemusic.ui.song.SongActivity
import com.frogobox.basemusic.ui.lyric.SongLyricActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun setupViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
    }

    override fun setupUI(savedInstanceState: Bundle?) {
        binding.apply {

            btnSimple.setOnClickListener {
                baseStartActivity<SongActivity>()
            }

            btnLyric.setOnClickListener {
                baseStartActivity<SongLyricActivity>()
            }

        }
    }
}