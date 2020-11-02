package com.example.artistlookup.Adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.artistlookup.R
import com.example.artistlookup.Database.Artist
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_artist.view.*


class ArtistAdapter(
    private val artist: List<Artist> = listOf(),
    private val onItemClick: (Artist) -> Unit,
    private val context: Context
) : RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ArtistViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_artist, parent, false)
    )
    override fun getItemCount() = artist.size

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.bindArtist(artist[position])
    }

    inner class ArtistViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindArtist(artist: Artist) {

            Picasso.with(context).load(artist.strArtistThumb).into(view.artistImg)
            view.artistName.text = artist.strArtist
            view.artistGenre.text = artist.strGenre

            view.setOnClickListener {
                onItemClick(artist)
            }
        }
    }
}