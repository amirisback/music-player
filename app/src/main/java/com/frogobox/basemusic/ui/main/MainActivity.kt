package com.frogobox.basemusic.ui.main

import android.os.Bundle
import com.frogobox.basemusic.core.BaseActivity
import com.frogobox.basemusic.databinding.ActivityMainBinding
import com.frogobox.basemusic.ui.lyric.LyricActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun setupViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
    }

    override fun setupUI(savedInstanceState: Bundle?) {
        binding.apply {

            btnSimple.setOnClickListener {

            }

            btnLyric.setOnClickListener {
                baseStartActivity<LyricActivity>()
            }

        }
    }
}