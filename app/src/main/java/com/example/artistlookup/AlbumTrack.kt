package com.example.artistlookup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.artistlookup.Adapter.AlbumTrackAdapter
import com.example.artistlookup.networking.AlbumTrackLoader
import kotlinx.android.synthetic.main.activity_album_track.*

class AlbumTrack : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "albumTrackInfo"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_track)

        val actionBar = supportActionBar
        actionBar!!.title = "ArtistLookUp"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)

        setupAlbumTrack()
    }

    private fun setupAlbumTrack(){
        AlbumTrackLoader(onSuccess = { it ->
            albumTrackList.layoutManager = LinearLayoutManager(this)
            albumTrackList!!.adapter = AlbumTrackAdapter(albumtrack = it.track, onItemClick = {
                Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show()

            })
        }, onError = {
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
        }).loadAlbumTrack(search = intent.getIntExtra(EXTRA_DATA, 1))
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
