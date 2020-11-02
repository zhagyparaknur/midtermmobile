package com.example.artistlookup.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.artistlookup.Database.AlbumTrack
import com.example.artistlookup.R
import kotlinx.android.synthetic.main.layout_album_track.view.*


class AlbumTrackAdapter(
    private val albumtrack: List<AlbumTrack> = listOf(),
    private val onItemClick: (AlbumTrack) -> Unit
) : RecyclerView.Adapter<AlbumTrackAdapter.AlbumTrackViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AlbumTrackViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_album_track, parent, false))

    override fun getItemCount() = albumtrack.size

    override fun onBindViewHolder(holder: AlbumTrackViewHolder, position: Int) {
        holder.bindAlbumTrack(albumtrack[position]) }

    inner class AlbumTrackViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindAlbumTrack(albumtrack: AlbumTrack) {
            view.albumTrackName.text = albumtrack.strTrack
            view.albumTrackDuration.text = albumtrack.intDuration.toString()

            view.setOnClickListener {
                onItemClick(albumtrack)
            }
        }
    }
}