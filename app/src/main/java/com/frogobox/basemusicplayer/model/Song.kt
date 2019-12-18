package com.frogobox.basemusicplayer.model

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * BaseMusicPlayer
 * Copyright (C) 19/12/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.basemusicplayer.model
 *
 */

data class Song(
    var songImage: Int = 0,
    var songMusic: Int = 0,
    var songName: String? = null,
    var artistName: String? = null,
    var albumName: String? = null

)