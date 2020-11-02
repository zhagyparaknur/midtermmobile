package com.example.artistlookup.Database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Favourite")
data class Favourite(
    @PrimaryKey @ColumnInfo(name = "id")val id: Int? =null,
    @ColumnInfo(name = "uid")val uid: String,
    @ColumnInfo(name = "aid")val aid: Int,
    @ColumnInfo(name = "strArtist")val strArtist: String,
    @ColumnInfo(name = "strGenre")val strGenre: String,
    @ColumnInfo(name = "strArtistThumb")val strArtistThumb: String
): Parcelable{
    constructor() : this(0,"",0,"","","")

}