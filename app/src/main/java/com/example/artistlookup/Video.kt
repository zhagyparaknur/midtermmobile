package com.example.artistlookup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.artistlookup.Adapter.VideoAdapter
import com.example.artistlookup.networking.VideoInfoLoader
import kotlinx.android.synthetic.main.activity_video.*
import java.lang.Integer.parseInt

class Video : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "videos"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        setupVideos()
        val actionBar = supportActionBar
        actionBar!!.title = "ArtistLookUp"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
    }


    private fun setupVideos() {
        VideoInfoLoader(onSuccess = { it ->
            videoList.layoutManager = LinearLayoutManager(this)
            videoList!!.adapter = VideoAdapter(
                videos = it.mvids,
                onItemClick ={ webBrowser.loadUrl(it.strMusicVid)}, context =
                this
            )
        }, onError = {
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
        }).loadVideoTrack(parseInt(intent.getStringExtra(EXTRA_DATA)))
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
