package com.example.artistlookup.Database

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AlbumTrack(
    val idArtist: Int,
    val strTrack: String,
    val intDuration: Int
) : Parcelable

data class AlbumTrackResponse(
    val track: List<AlbumTrack>
)

