package com.example.artistlookup.networking

import com.example.artistlookup.Database.AlbumResponse
import com.example.artistlookup.Database.TrackResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TrackInfoLoader(
    private val onSuccess: (TrackResponse) -> Unit,
    private val onError: (Throwable) -> Unit
) {
    fun loadTrack(search: String) {
        ApiFactory.getApiArtist().getArtistTopTrack(search).enqueue(object : Callback<TrackResponse> {
            override fun onResponse(call: Call<TrackResponse>, response: Response<TrackResponse>) {
                onSuccess(response.body()!!)
            }
            override fun onFailure(call: Call<TrackResponse>, t: Throwable) {
                onError(t)
            }
        })
    }
}