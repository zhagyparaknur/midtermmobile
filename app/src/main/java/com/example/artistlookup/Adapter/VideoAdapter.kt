package com.example.artistlookup.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.artistlookup.Database.Video
import com.example.artistlookup.R
import com.google.android.youtube.player.YouTubePlayer
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_toptracks.view.*
import kotlinx.android.synthetic.main.layout_video.view.*
import kotlinx.android.synthetic.main.layout_video.view.trackImg


class VideoAdapter(
    private val videos: List<Video> = listOf(),
    private val onItemClick: (Video) -> Unit,
    private val context: Context
) : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VideoViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_video, parent, false)
    )
    override fun getItemCount() = videos.size

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bindArtist(videos[position])
    }

    lateinit var youtubePlayerInit: YouTubePlayer.OnInitializedListener


    inner class VideoViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindArtist(videos: Video) {
            Picasso.with(context).load(videos.strTrackThumb).into(view.trackImg)
            view.videoTitle.text = videos.strTrack
            view.setOnClickListener {
                onItemClick(videos)
             }
            }
        }
    }
