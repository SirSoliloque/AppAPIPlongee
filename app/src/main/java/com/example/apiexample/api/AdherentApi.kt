package com.example.apiexample.api

import android.util.Log
import androidx.compose.runtime.MutableState
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

public interface AdherentApi {
    @GET("adherents")
    fun getAdherents(): Call<List<AdherentModel>>

    @GET("adherents/{id}")
    fun getAdherent(@Path("id") id: Int): Call<AdherentModel>

    @PUT("adherents/{id}")
    fun putAdherent(@Path("id") id: Int, @Body adherent: AdherentModel): Call<Unit>

    @POST("adherents")
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

    fun getAdherents(adherentList: MutableList<AdherentModel>){
        val apiService = retrofit.create(AdherentApi::class.java)
        val call = apiService.getAdherents()
        Log.println(Log.INFO,"main","ici")
        val response = call.execute();
        if(response.isSuccessful){
            adherentList.clear()
            adherentList.addAll(response.body()?.toMutableList()!!)
        }else{

            Log.e("Main","Error on getAdherent request"+ (response.errorBody()?.string() ))
        }
    }

    fun getAdherent(id: Int, adherent: MutableState<AdherentModel>) {

        val apiService = retrofit.create(AdherentApi::class.java)
        val call = apiService.getAdherent(id)
        Log.println(Log.INFO,"main","ici")
        val response = call.execute();
        if(response.isSuccessful){
            adherent.value= response.body()!!
        }else{

            Log.e("Main","Error on getAdherent request"+ (response.message() ))
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
