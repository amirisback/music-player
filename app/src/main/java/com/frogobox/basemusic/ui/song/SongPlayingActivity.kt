package com.frogobox.basemusic.ui.song

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import com.bumptech.glide.Glide
import com.frogobox.basemusic.core.BaseActivity
import com.frogobox.basemusic.databinding.ActivitySongPlayingBinding
import com.frogobox.basemusic.model.Song
import com.frogobox.basemusic.util.ConstHelper.Extra.EXTRA_SONG


class SongPlayingActivity : BaseActivity<ActivitySongPlayingBinding>() {

    private var mMediaPlayer: MediaPlayer? = null

    override fun setupViewBinding(): ActivitySongPlayingBinding {
        return ActivitySongPlayingBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
    }

    override fun setupUI(savedInstanceState: Bundle?) {
        setupDetailActivity("")
        setupShowAdsBanner(binding.ads.adsBanner)

        val extraSong = baseGetExtraData<Song>(EXTRA_SONG)

        binding.apply {
            seekBar.isEnabled = false
            artistName.text = extraSong.artistName
            songName.text = extraSong.songName
            Glide.with(this@SongPlayingActivity).load(extraSong.songImage).into(songImage)

            play.setOnClickListener { playSong(extraSong.songMusic) }
            stop.setOnClickListener { stopSong() }
            pause.setOnClickListener { pauseSong() }

            seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
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

    }


    private fun playSong(songMusic: Int) {
        binding.apply {
            seekBar.isEnabled = true
            if (mMediaPlayer == null) {
                mMediaPlayer = MediaPlayer.create(this@SongPlayingActivity, songMusic)
                mMediaPlayer!!.start()
                seekBar.max = mMediaPlayer!!.duration / 1000
            } else {
                mMediaPlayer!!.start()
                seekBar.max = mMediaPlayer!!.duration / 1000
            }
            runOnUiThread(
                object : Runnable {
                    override fun run() {
                        if (mMediaPlayer != null) {
                            val mCurrentPosition = mMediaPlayer!!.currentPosition / 1000
                            seekBar.progress = mCurrentPosition
                        }
                        Handler().postDelayed(this, 1000);
                    }
                }
            )
        }
    }


    private fun pauseSong() {
        binding.apply {
            seekBar.isEnabled = false
            if (mMediaPlayer != null) {
                mMediaPlayer!!.pause()
            }
        }
    }

    private fun stopSong() {
        binding.apply {
            seekBar.isEnabled = false
            seekBar.progress = 0
            if (mMediaPlayer != null) {
                mMediaPlayer!!.stop()
                releaseMediaPlayer()
            }
        }
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