package com.example.artistlookup.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.artistlookup.Database.Album
import com.example.artistlookup.R
import kotlinx.android.synthetic.main.layout_album.view.*


class AlbumAdapter(
    private val album: List<Album> = listOf(),
    private val onItemClick: (Album) -> Unit
) : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AlbumViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_album, parent, false))

    override fun getItemCount() = album.size

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bindAlbum(album[position]) }

    inner class AlbumViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindAlbum(album: Album) {
            view.albumName.text = album.strAlbum
            view.albumRelase.text = album.intYearReleased
            view.albumGenre.text = album.strGenre
            view.setOnClickListener {
                onItemClick(album)
            }
        }
    }
}