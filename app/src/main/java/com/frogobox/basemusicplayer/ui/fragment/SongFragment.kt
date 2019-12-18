package com.frogobox.basemusicplayer.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.frogobox.basemusicplayer.R
import com.frogobox.basemusicplayer.base.ui.BaseFragment

/**
 * A simple [Fragment] subclass.
 */
class SongFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_song, container, false)
    }


}
