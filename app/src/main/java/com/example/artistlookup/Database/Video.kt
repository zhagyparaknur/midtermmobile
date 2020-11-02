package com.example.artistlookup.Database

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Video(
    val idArtist: Int,
    val strTrack: String,
    val strTrackThumb: String,
    val strMusicVid: String
) : Parcelable

data class VideoResponse(
    val mvids: List<Video>
)

