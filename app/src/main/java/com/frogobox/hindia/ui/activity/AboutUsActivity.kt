package com.frogobox.hindia.ui.activity

import android.os.Bundle
import com.frogobox.hindia.R
import com.frogobox.hindia.base.admob.BaseAdmobActivity

class AboutUsActivity : BaseAdmobActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)
        setupDetailActivity("")
    }
}
