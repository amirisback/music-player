package com.frogobox.basemusic.ui.song

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.frogobox.basemusic.R
import com.frogobox.basemusic.core.BaseActivity
import com.frogobox.basemusic.databinding.ActivitySongBinding
import com.frogobox.basemusic.ui.main.AboutUsActivity

class SongActivity : BaseActivity<ActivitySongBinding>() {

    override fun setupViewBinding(): ActivitySongBinding {
        return ActivitySongBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
    }

    override fun setupUI(savedInstanceState: Bundle?) {
        setupToolbar()
        setupCustomTitleToolbar(R.string.title_song)
        setupChildFragment(binding.framelayoutMainContainer.id, SongFragment())
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar.toolbarMain)
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
