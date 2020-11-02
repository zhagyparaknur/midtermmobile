package com.example.artistlookup

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.artistlookup.Adapter.ArtistAdapter
import com.example.artistlookup.Database.Artist
import com.example.artistlookup.networking.ArtistInfoLoader
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_artist.*


class MainActivity : AppCompatActivity() {
    private val auth by lazy {
        FirebaseAuth.getInstance()
    }

    private val database by lazy {
        FirebaseFirestore.getInstance()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun search(searchInp: String) {

        val intent = Intent(this, ArtistDetail::class.java)
        ArtistInfoLoader(onSuccess = { it ->
            artistList.layoutManager = LinearLayoutManager(this)
            artistList!!.adapter = ArtistAdapter(artist = it.artists, onItemClick = {
                sendInfotoDetail(it, intent)
                addToFavourite(it)

            }, context = this)
            }, onError = {
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
        }).loadArtist(searchInp)
    }

    private fun sendInfotoDetail(it: Artist, intent: Intent) {
        intent.putExtra(
            ArtistDetail.EXTRA_DATA,
            Artist(
                idArtist = it.idArtist,
                strArtist = it.strArtist,
                strGenre = it.strGenre,
                strArtistThumb = it.strArtistThumb,
                strBiographyEN = it.strBiographyEN,
                intBornYear = it.intBornYear,
                strArtistLogo = it.strArtistLogo,
                strWebsite = it.strWebsite
            )
        )
        startActivity(intent)
    }

    private fun addToFavourite(art: Artist) {
        favBtn.setOnClickListener {
            val fav= hashMapOf(
                    "uid" to auth.uid.toString(),
                    "aid" to art.idArtist,
                    "strArtist" to art.strArtist,
                    "strGenre" to art.strGenre,
                    "strArtistThumb" to art.strArtistThumb
            )
            database.collection("Favourites").add(fav)
            Toast.makeText(this, "Added to Favourite", Toast.LENGTH_LONG).show()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.searchInput)
        val searchView = searchItem?.actionView as SearchView

        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                searchView.setQuery("", false)
                searchItem.collapseActionView()
                if (query != null) {
                    search(query)
                }else{
                    return false
                }
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.logout) {
            auth.signOut()
            startActivity(Intent(this, SignIn::class.java))
            return true
        }
        if (item.itemId == R.id.favourite) {
            startActivity(Intent(this, Favourite::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}
