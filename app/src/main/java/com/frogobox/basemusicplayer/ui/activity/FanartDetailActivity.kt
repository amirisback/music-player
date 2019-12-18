package com.frogobox.basemusicplayer.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.frogobox.basemusicplayer.R
import com.frogobox.basemusicplayer.base.admob.BaseAdmobActivity
import com.frogobox.basemusicplayer.util.helper.ConstHelper.Extra.EXTRA_FANART
import kotlinx.android.synthetic.main.activity_fashion_detail.*
import kotlinx.android.synthetic.main.ads_phone_tab_banner.*

class FanartDetailActivity : BaseAdmobActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fashion_detail)
        setupDetailActivity("")
        setupImageView()
        setupShowAdsBanner(ads_phone_tab_banner)
    }

    private fun setupImageView() {
        val image = intent.getStringExtra(EXTRA_FANART)
        Glide.with(this).load(image).into(iv_poster)
    }

    private fun setupIntentData() {
        val image = intent.getStringExtra(EXTRA_FANART)
        startActivity(
            Intent(
                this,
                FanartSourceActivity::class.java
            ).putExtra(EXTRA_FANART, image)
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.toolbar_menu_copyright -> {
                setupIntentData()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
