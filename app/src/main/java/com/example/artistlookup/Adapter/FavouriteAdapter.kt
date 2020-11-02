package com.example.artistlookup.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.artistlookup.R
import com.example.artistlookup.Database.Favourite
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_artist.view.*


class FavouriteAdapter(
    private val favourite: List<Favourite> = listOf(),
    private val context: Context
) : RecyclerView.Adapter<FavouriteAdapter.FavouritesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FavouritesViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_favourite, parent, false)
    )
    override fun getItemCount() = favourite.size

    override fun onBindViewHolder(holder: FavouritesViewHolder, position: Int) {
        holder.bindArtist(favourite[position])
    }

    inner class FavouritesViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindArtist(favourite: Favourite) {

            Picasso.with(context).load(favourite.strArtistThumb).into(view.artistImg)
            view.artistName.text = favourite.strArtist
            view.artistGenre.text = favourite.strGenre

        }
    }
}