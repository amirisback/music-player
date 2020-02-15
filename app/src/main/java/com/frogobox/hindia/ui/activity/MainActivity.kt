package com.frogobox.hindia.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.frogobox.hindia.R
import com.frogobox.hindia.base.admob.BaseAdmobActivity
import com.frogobox.hindia.ui.fragment.SongFragment
import kotlinx.android.synthetic.main.ads_banner.*
import kotlinx.android.synthetic.main.toolbar_main.*

class MainActivity : BaseAdmobActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar()
        setupShowAdsBanner(ads_banner)
        setupChildFragment(R.id.framelayout_main_container, SongFragment())
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

}
