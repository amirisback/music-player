package com.frogobox.basemusicplayer.ui.activity

import android.os.Bundle
import com.frogobox.basemusicplayer.R
import com.frogobox.basemusicplayer.base.admob.BaseAdmobActivity

class AboutUsActivity : BaseAdmobActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)
        setupDetailActivity("")
    }
}
