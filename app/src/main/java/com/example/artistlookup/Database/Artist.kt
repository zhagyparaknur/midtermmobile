package com.example.artistlookup.Database

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Artist(
    val idArtist: Int,
    val strArtist: String,
    val strGenre: String,
    val strArtistThumb: String,
    val strBiographyEN: String,
    val intBornYear: String,
    val strArtistLogo: String,
    val strWebsite: String
) : Parcelable {
    constructor(idArtist: List<Artist>) : this(0,"","","","","","","")
}

data class ArtistResponse(
    val artists: List<Artist>
)

