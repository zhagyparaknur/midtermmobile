package com.example.artistlookup.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.artistlookup.Database.Track
import com.example.artistlookup.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_toptracks.view.*


class TrackAdapter(
    private val track: List<Track> = listOf(),
    private val onItemClick: (Track) -> Unit,
    private val context: Context
) : RecyclerView.Adapter<TrackAdapter.TrackViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TrackViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_toptracks, parent, false)
    )
    override fun getItemCount() = track.size

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.bindArtist(track[position])
    }

    inner class TrackViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindArtist(track: Track) {

            Picasso.with(context).load(track.strTrackThumb).into(view.trackImg)
            view.albumName.text = track.strAlbum
            view.trackName.text = track.strTrack
            view.trackGenre.text = track.strGenre
            view.trackDuration.text = track.intDuration.toString()
            view.trackMood.text = track.strMood

            view.setOnClickListener {
                onItemClick(track)
            }
        }
    }
}