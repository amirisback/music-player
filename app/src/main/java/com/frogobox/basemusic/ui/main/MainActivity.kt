package com.frogobox.basemusic.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.frogobox.basemusic.R
import com.frogobox.basemusic.core.BaseActivity
import com.frogobox.basemusic.databinding.ActivityMainBinding
import com.frogobox.basemusic.ui.song.SongFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun setupViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
    }

    override fun setupUI(savedInstanceState: Bundle?) {
        setSupportActionBar(binding.toolbar.toolbarMain)
        setupShowAdsBanner(binding.ads.adsBanner)
        setupChildFragment(binding.framelayoutMainContainer.id, SongFragment())
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