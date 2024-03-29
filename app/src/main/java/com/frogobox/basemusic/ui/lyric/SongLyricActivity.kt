package com.frogobox.basemusic.ui.lyric

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.frogobox.basemusic.R
import com.frogobox.basemusic.core.BaseActivity
import com.frogobox.basemusic.databinding.ActivitySongLyricBinding
import com.frogobox.basemusic.ui.main.AboutUsActivity

class SongLyricActivity : BaseActivity<ActivitySongLyricBinding>() {

    override fun setupViewBinding(): ActivitySongLyricBinding {
        return ActivitySongLyricBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
    }

    override fun setupUI(savedInstanceState: Bundle?) {
        setSupportActionBar(binding.toolbar.toolbarMain)
        setupShowAdsBanner(binding.ads.adsBanner)
        setupChildFragment(binding.framelayoutMainContainer.id, SongLyricFragment())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.toolbar_menu_about -> {
                baseStartActivity<AboutUsActivity>()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}