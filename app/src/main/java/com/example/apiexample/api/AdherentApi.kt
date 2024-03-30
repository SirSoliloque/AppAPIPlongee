package com.example.apiexample.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

public interface AdherentApi {
    @GET("/adherent")
    fun getAdherents(): List<AdherentModel>

    @GET("/adherent/{id}")
    fun getAdherent(@Path("id") id: Int): Call<AdherentModel>

    @PUT("/adherent/{id}")
    fun putAdherent(@Path("id") id: Int, @Body adherent: AdherentModel): Call<Unit>

    @POST("/adherent")
    fun postAdherent(@Body adherent: AdherentModel): Call<AdherentModel>
}

class ApiClient {

    private val baseUrl = "https://dev-restandroid.users.info.unicaen.fr/api/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }



    fun getAdherent(id: Int): AdherentModel {
        val apiService = retrofit.create(AdherentApi::class.java)
        val call = apiService.getAdherent(id)
        val response = call.execute()
        return if (response.isSuccessful) {
            response.body()!!
        } else {
            throw Exception("Error fetching adherent")
        }
    }

    fun putAdherent(adherent: AdherentModel) {
        val apiService = retrofit.create(AdherentApi::class.java)
        val call = apiService.putAdherent(adherent.id, adherent)
        call.execute()
    }

    fun postAdherent(adherent: AdherentModel): AdherentModel {
        val apiService = retrofit.create(AdherentApi::class.java)
        val call = apiService.postAdherent(adherent)
        val response = call.execute()
        return if (response.isSuccessful) {
            response.body()!!
        } else {
            throw Exception("Error creating adherent")
        }
    }
}
