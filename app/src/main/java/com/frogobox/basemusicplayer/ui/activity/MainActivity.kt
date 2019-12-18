package com.frogobox.basemusicplayer.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.frogobox.basemusicplayer.R
import com.frogobox.basemusicplayer.base.admob.BaseAdmobActivity
import com.frogobox.basemusicplayer.ui.fragment.FanartFragment
import com.frogobox.basemusicplayer.ui.fragment.SongFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_main.*

class MainActivity : BaseAdmobActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar()
        setupBottomNav(R.id.framelayout_main_container)
        setupFragment(savedInstanceState)
    }

    private fun setupFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            bottom_nav_main_menu.selectedItemId = R.id.bottom_menu_song
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar_main)
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

    private fun setupBottomNav(frameLayout: Int) {
        bottom_nav_main_menu.clearAnimation()
        bottom_nav_main_menu.itemIconTintList = null
        bottom_nav_main_menu.setOnNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.bottom_menu_song -> {
                    setupCustomTitleToolbar(R.string.title_song)
                    setupChildFragment(frameLayout, SongFragment())
                }

                R.id.bottom_menu_fanart -> {
                    setupCustomTitleToolbar(R.string.title_fanart)
                    setupChildFragment(frameLayout, FanartFragment())
                    setupShowAdsInterstitial()
                }

            }

            true
        }

    }

}
