package com.frogobox.basemusicplayer.ui.activity

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import com.bumptech.glide.Glide
import com.frogobox.basemusicplayer.R
import com.frogobox.basemusicplayer.base.admob.BaseAdmobActivity
import com.frogobox.basemusicplayer.model.Song
import com.frogobox.basemusicplayer.util.helper.ConstHelper.Extra.EXTRA_SONG
import kotlinx.android.synthetic.main.activity_song_playing.*

class SongPlayingActivity : BaseAdmobActivity() {

    private var mMediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_playing)
        setupDetailActivity("")
        setupComponentView()
    }

    private fun setupComponentView() {
        val extraSong = baseGetExtraData<Song>(EXTRA_SONG)

        seekBar.isEnabled = false
        artist_name.text = extraSong.artistName
        song_name.text = extraSong.songName
        Glide.with(this).load(extraSong.songImage).into(song_image)

        setupButton(extraSong.songMusic)
    }

    private fun setupButton(song: Int) {
        play.setOnClickListener { playSong(song) }
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

    private fun playSong(songMusic: Int) {
        seekBar.isEnabled = true
        if (mMediaPlayer == null) {
            mMediaPlayer = MediaPlayer.create(this, songMusic)
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


    private fun pauseSong() {
        seekBar.isEnabled = false
        if (mMediaPlayer != null) {
            mMediaPlayer!!.pause()
        }
    }

    private fun stopSong() {
        seekBar.isEnabled = false
        seekBar.progress = 0
        if (mMediaPlayer != null) {
            mMediaPlayer!!.stop()
            releaseMediaPlayer()
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