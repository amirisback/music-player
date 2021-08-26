package com.frogobox.basemusic.util

import android.Manifest
import android.os.Environment
import com.frogobox.basemusic.BuildConfig

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * PublicSpeakingBooster
 * Copyright (C) 16/08/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.publicspeakingbooster.helper
 *
 */
class ConstHelper {

    object Extra {
        const val BASE_EXTRA = BuildConfig.APPLICATION_ID
        const val EXTRA_SONG = "$BASE_EXTRA.EXTRA_SONG"
    }

    object Ext {
        const val DEF_DRAWABLE = "drawable"
        const val DEF_RAW = "raw"
        const val MP4 = ".mp4"
        const val PNG = ".png"
    }

}