package com.frogobox.basemusicplayer

import android.os.Bundle
import com.frogobox.basemusicplayer.base.admob.BaseAdmobActivity
import com.frogobox.basemusicplayer.base.ui.BaseActivity
import kotlinx.android.synthetic.main.ads_phone_tab_special_smart_banner.*

class MainActivity : BaseAdmobActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupShowAdsBanner(ads_phone_tab_special_smart_banner)

    }



}
