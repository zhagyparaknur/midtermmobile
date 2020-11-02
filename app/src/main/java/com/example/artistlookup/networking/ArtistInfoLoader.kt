package com.example.artistlookup.networking

import com.example.artistlookup.Database.ArtistResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArtistInfoLoader(
    private val onSuccess: (ArtistResponse) -> Unit,
    private val onError: (Throwable) -> Unit
) {
    fun loadArtist(search: String) {
        ApiFactory.getApiArtist().getArtistByName(search).enqueue(object : Callback<ArtistResponse> {
            override fun onResponse(call: Call<ArtistResponse>, response: Response<ArtistResponse>) {
                onSuccess(response.body()!!)
            }
            override fun onFailure(call: Call<ArtistResponse>, t: Throwable) {
                onError(t)
            }
        })
    }
}