package com.frogobox.basemusicplayer.base.admob

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.frogobox.basemusicplayer.base.ui.BaseActivity
import com.frogobox.basemusicplayer.helper.AdmobHelper.Banner.setupBanner
import com.frogobox.basemusicplayer.helper.AdmobHelper.Banner.showBanner
import com.frogobox.basemusicplayer.helper.AdmobHelper.Interstitial.setupInterstitial
import com.frogobox.basemusicplayer.helper.AdmobHelper.Interstitial.showInterstitial
import com.frogobox.basemusicplayer.helper.AdmobHelper.Publisher.setupPublisher
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.reward.RewardedVideoAd

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * ImplementationAdmob
 * Copyright (C) 31/10/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.basemusicplayer
 *
 */

open class BaseAdmobActivity : BaseActivity() {

    protected lateinit var mActivity: AppCompatActivity
    lateinit var mInterstitialAd: InterstitialAd
    private lateinit var mRewardedVideoAd: RewardedVideoAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = this
        setupAdmob()
    }

    private fun setupAdmob() {
        setupPublisher(this)
        setupAdmobInterstitial()
//        setupAdmobVideo(context)
    }

    private fun setupAdmobInterstitial() {
        mInterstitialAd = InterstitialAd(this)
        setupInterstitial(this, mInterstitialAd)
    }

    fun setupShowAdsInterstitial() {
        showInterstitial(mInterstitialAd)
    }

    fun setupShowAdsBanner(mAdView : AdView) {
        setupBanner(mAdView)
        showBanner(mAdView)
    }

}