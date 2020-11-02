package com.example.artistlookup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.artistlookup.Database.Artist
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_artist_detail.*

class ArtistDetail : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "artistInfo"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist_detail)

        val actionBar = supportActionBar
        actionBar!!.title = "ArtistLookUp"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)

        val item = intent.getParcelableExtra<Artist>(EXTRA_DATA)
        Picasso.with(this).load(item.strArtistThumb).into(artistImg)
        Picasso.with(this).load(item.strArtistLogo).into(artistLogo)
        artistName.text = item.strArtist
        artisBornYear.text  = item.intBornYear
        artistGenre.text = item.strGenre
        artistBiography.text = item.strBiographyEN
        toAlbum(item.strArtist)
        toTopTrack(item.strArtist)
        toVideos(item.idArtist.toString())
        goToSite(item.strWebsite)
    }

    private fun toVideos(artistID: String){
        val intent = Intent(this, Video::class.java)
        videoBtn.setOnClickListener{
            intent.putExtra(Video.EXTRA_DATA, artistID)
            startActivity(intent)
        }
    }

   private fun toAlbum(artistNameForAlbum: String){
       val intent = Intent(this, Albums::class.java)
        albumBtn.setOnClickListener{
            intent.putExtra(Albums.EXTRA_DATA, artistNameForAlbum)
            startActivity(intent)
        }
    }

    private fun toTopTrack(artistNameForTop: String){
        val intent = Intent(this, TopTracks::class.java)
        topTrack.setOnClickListener{
            intent.putExtra(TopTracks.EXTRA_DATA, artistNameForTop)
            startActivity(intent)
        }
    }

    private fun goToSite(link: String){
        webSite.setOnClickListener {
            Toast.makeText(this, link, Toast.LENGTH_LONG).show()
            goToWebBrowser.loadUrl("https://$link")
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
