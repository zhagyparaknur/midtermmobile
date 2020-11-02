package com.example.artistlookup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.artistlookup.Adapter.AlbumAdapter
import com.example.artistlookup.Database.Album
import com.example.artistlookup.networking.AlbumInfoLoader
import kotlinx.android.synthetic.main.activity_albums.*

class Albums : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "albumInfo"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albums)
        setupAlbums()


        val actionBar = supportActionBar
        actionBar!!.title = "ArtistLookUp"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupAlbums() {
        AlbumInfoLoader(onSuccess = { it ->
            albumList.layoutManager = LinearLayoutManager(this)
            albumList!!.adapter = AlbumAdapter(album = it.album, onItemClick = {
                sendInfotoDetail(it, Intent(this, AlbumDetail::class.java))
            })
        }, onError = {
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
        }).loadAlbum(intent.getStringExtra(EXTRA_DATA))
    }

    private fun sendInfotoDetail(it: Album, intent: Intent) {
        intent.putExtra(
            AlbumDetail.EXTRA_DATA,
            Album(
                idAlbum = it.idAlbum,
                idArtist = it.idArtist,
                strAlbum = it.strAlbum,
                strArtistStripped = it.strArtistStripped,
                intYearReleased = it.intYearReleased,
                strGenre = it.strGenre,
                strAlbumThumb = it.strAlbumThumb,
                strDescriptionEN = it.strDescriptionEN
            )
        )
        startActivity(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
