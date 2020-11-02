package com.example.artistlookup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.artistlookup.Database.Album
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_album_detail.*

class AlbumDetail : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "albumInfo"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_detail)

        val actionBar = supportActionBar
        actionBar!!.title = "ArtistLookUp"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)

        val item = intent.getParcelableExtra<Album>(EXTRA_DATA)
        Picasso.with(this).load(item.strAlbumThumb).into(albumImg)
        albumName.text = item.strAlbum
        artistName.text = item.strArtistStripped
        albumDescription.text = item.strDescriptionEN

        toAlbumTrack(item.idAlbum)
    }

    private fun toAlbumTrack(albumId: Int){
        val intent = Intent(this, AlbumTrack::class.java)
        albumTracks.setOnClickListener {
            intent.putExtra(AlbumTrack.EXTRA_DATA, albumId)
            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
