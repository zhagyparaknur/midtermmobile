package com.example.artistlookup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.artistlookup.Adapter.FavouriteAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.example.artistlookup.Database.Favourite
import kotlinx.android.synthetic.main.activity_favourite.*

class Favourite : AppCompatActivity() {

    private val auth by lazy {
        FirebaseAuth.getInstance()
    }

    private val database by lazy {
        FirebaseFirestore.getInstance()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourite)
        val actionBar = supportActionBar
        actionBar!!.title = "ArtistLookUp"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)

        setupFavourites()
    }

    private fun setupFavourites(){

        favList.layoutManager = LinearLayoutManager(this)
        database.collection("Favourites").whereEqualTo("uid", auth.currentUser!!.uid)
            .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                val favs = querySnapshot?.documents?.map {
                    it.toObject(Favourite::class.java)
                }
                favList!!.adapter = FavouriteAdapter(favs as List<Favourite>,this)
            }
    }


    private fun removeFav(id: Int){
        database.collection("Favourites").whereEqualTo("aid", id).addSnapshotListener {
                querySnapshot, firebaseFirestoreException ->
                querySnapshot?.documents?.map {
                }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
