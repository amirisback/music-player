package com.frogobox.basemusic.ui.lyric

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import com.frogobox.basemusic.core.BaseActivity
import com.frogobox.basemusic.databinding.ActivitySongLyricPlayingBinding
import com.frogobox.basemusic.model.SongLyric
import com.frogobox.basemusic.util.ConstHelper.Extra.EXTRA_SONG
import com.frogobox.basemusic.util.RawDataHelper

class SongLyricPlayingActivity : BaseActivity<ActivitySongLyricPlayingBinding>() {

    private var mMediaPlayer: MediaPlayer? = null

    override fun setupViewBinding(): ActivitySongLyricPlayingBinding {
        return ActivitySongLyricPlayingBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
    }

    override fun setupUI(savedInstanceState: Bundle?) {
        setupDetailActivity("")
        setupComponentView()
        setupShowAdsBanner(binding.adsBanner)
    }

    private fun arraySongData(lyric: Int): String {
        val lyricArrayString = RawDataHelper()
            .fetchData(this, lyric)
        var lyrics = ""
        for (i in lyricArrayString) {
            lyrics = lyrics + i + "\n"
        }
        return lyrics
    }

    private fun setupComponentView() {
        val extraSong = baseGetExtraData<SongLyric>(EXTRA_SONG)

        binding.seekBar.isEnabled = false
        binding.songName.text = extraSong.songName
        binding.tvLyrics.text = arraySongData(extraSong.songLyric)
        setupButton(extraSong.songMusic)
    }

    private fun setupButton(song: Int) {
        binding.play.setOnClickListener { playSong(song) }
        binding.stop.setOnClickListener { stopSong() }
        binding.pause.setOnClickListener { pauseSong() }

        binding.seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            var seeked_progess = 0
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                seeked_progess = i
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                mMediaPlayer!!.seekTo(seeked_progess * 1000 % mMediaPlayer!!.duration)
            }
        })

    }

    private fun playSong(songMusic: Int) {
        binding.seekBar.isEnabled = true
        if (mMediaPlayer == null) {
            mMediaPlayer = MediaPlayer.create(this, songMusic)
            mMediaPlayer!!.start()
            binding.seekBar.max = mMediaPlayer!!.duration / 1000
        } else {
            mMediaPlayer!!.start()
            binding.seekBar.max = mMediaPlayer!!.duration / 1000
        }
        runOnUiThread(
            object : Runnable {
                override fun run() {
                    if (mMediaPlayer != null) {
                        val mCurrentPosition = mMediaPlayer!!.currentPosition / 1000
                        binding.seekBar.progress = mCurrentPosition
                    }
                    Handler().postDelayed(this, 1000);
                }
            }
        )
    }


    private fun pauseSong() {
        binding.seekBar.isEnabled = false
        if (mMediaPlayer != null) {
            mMediaPlayer!!.pause()
        }
        setupShowAdsInterstitial()
    }

    private fun stopSong() {
        binding.seekBar.isEnabled = false
        binding.seekBar.progress = 0
        if (mMediaPlayer != null) {
            mMediaPlayer!!.stop()
            releaseMediaPlayer()
        }
        setupShowAdsInterstitial()
    }

    private fun releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer!!.release()
            mMediaPlayer = null
        }
    }

    override fun onBackPressed() {
        releaseMediaPlayer()
        finish()
    }


}