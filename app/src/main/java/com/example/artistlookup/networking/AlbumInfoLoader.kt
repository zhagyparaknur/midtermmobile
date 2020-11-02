package com.example.artistlookup.networking

import com.example.artistlookup.Database.AlbumResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlbumInfoLoader(
    private val onSuccess: (AlbumResponse) -> Unit,
    private val onError: (Throwable) -> Unit
) {
    fun loadAlbum(search: String) {
        ApiFactory.getApiArtist().getAlbumbyName(search).enqueue(object : Callback<AlbumResponse> {
            override fun onResponse(call: Call<AlbumResponse>, response: Response<AlbumResponse>) {
                onSuccess(response.body()!!)
            }
            override fun onFailure(call: Call<AlbumResponse>, t: Throwable) {
                onError(t)
            }
        })
    }
}