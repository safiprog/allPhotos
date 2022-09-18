package com.example.allpicture

import com.example.allpicture.dat.ImageData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

//api.unsplash.com/photos?client_id=Z7_Fz6ypAqxq8K9qIhOsCDTBirYmrttl965IZl5_Cn0&page=1&per_page=30
const val BASE_URL="https://api.unsplash.com"
const val clientId="Z7_Fz6ypAqxq8K9qIhOsCDTBirYmrttl965IZl5_Cn0"
interface photosApi {
    @Headers("Authorization: Client-ID " + clientId)
    @GET("/search/photos")
    fun getImage(@Query("page")page:Int,@Query("query")query:String):Call<ImageData>
}
object imageServices{
    val photoInstans:photosApi
    init {
        val retrofit=Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        photoInstans=retrofit.create(photosApi::class.java)
    }
}