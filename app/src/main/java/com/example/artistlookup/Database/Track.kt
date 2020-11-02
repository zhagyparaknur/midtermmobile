package com.example.artistlookup.Database


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Track(
    val strTrack: String,
    val strAlbum: String,
    val intDuration: Int,
    val strGenre: String,
    val strTrackThumb: String,
    val strMood: String
) : Parcelable

data class TrackResponse(
    val track: List<Track>
)

