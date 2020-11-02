package com.example.artistlookup.networking

import com.example.artistlookup.Database.AlbumTrackResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlbumTrackLoader(
    private val onSuccess: (AlbumTrackResponse) -> Unit,
    private val onError: (Throwable) -> Unit
) {
    fun loadAlbumTrack(search: Int) {
        ApiFactory.getApiArtist().getAlbumTrack(search).enqueue(object : Callback<AlbumTrackResponse> {
            override fun onResponse(call: Call<AlbumTrackResponse>, response: Response<AlbumTrackResponse>) {
                onSuccess(response.body()!!)
            }
            override fun onFailure(call: Call<AlbumTrackResponse>, t: Throwable) {
                onError(t)
            }
        })
    }
}