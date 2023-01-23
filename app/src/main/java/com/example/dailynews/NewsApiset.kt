package com.example.dailynews
import retrofit2.*
import retrofit2.http.GET

interface NewsApiset {

    @GET("top-headlines?country=in&apiKey=7b4d8d6d4fb940c7b3d0018ace51d76e")
    fun getNewsData() : Call<RNews>
}