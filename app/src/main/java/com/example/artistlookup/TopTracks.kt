package com.example.artistlookup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.artistlookup.Adapter.TrackAdapter
import com.example.artistlookup.networking.TrackInfoLoader
import kotlinx.android.synthetic.main.activity_top_tracks.*

class TopTracks : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "toptrack"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_tracks)
        setupTopTracks()
        val actionBar = supportActionBar
        actionBar!!.title = "ArtistLookUp"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupTopTracks(){
        TrackInfoLoader(onSuccess = { it ->
            topTrackList.layoutManager = LinearLayoutManager(this)
            topTrackList!!.adapter = TrackAdapter(track = it.track, onItemClick = {
                Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show()
            },context = this)
        }, onError = {
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
        }).loadTrack(intent.getStringExtra(EXTRA_DATA))
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
