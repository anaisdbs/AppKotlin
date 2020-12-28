package com.example.appkotlin.data.remote.api

//import android.telecom.Call
import com.example.appkotlin.data.remote.RecyclerList
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call

interface RetroService {

    @GET("repositories")
    fun getDataFromApi(@Query("q") query: String): Call<RecyclerList>
}