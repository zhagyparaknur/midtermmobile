package com.example.artistlookup.networking

import com.example.artistlookup.Database.VideoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VideoInfoLoader(
    private val onSuccess: (VideoResponse) -> Unit,
    private val onError: (Throwable) -> Unit
) {
    fun loadVideoTrack(search: Int) {
        ApiFactory.getApiArtist().getVideoByArtist(search).enqueue(object : Callback<VideoResponse> {
            override fun onResponse(call: Call<VideoResponse>, response: Response<VideoResponse>) {
                onSuccess(response.body()!!)
            }
            override fun onFailure(call: Call<VideoResponse>, t: Throwable) {
                onError(t)
            }
        })
    }
}