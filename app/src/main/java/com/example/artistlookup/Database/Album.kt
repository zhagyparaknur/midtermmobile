package com.example.artistlookup.Database

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Album(
    val idAlbum: Int,
    val idArtist: Int,
    val strAlbum: String,
    val strArtistStripped: String,
    val intYearReleased: String,
    val strGenre: String,
    val strAlbumThumb: String,
    val strDescriptionEN: String
) : Parcelable

data class AlbumResponse(
    val album: List<Album>
)

