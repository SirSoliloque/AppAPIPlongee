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


public interface PlongeeApi {
    @GET("dives")
    fun getPlongees(): Call<List<PlongeeModel>>

    @GET("dives/{id}")
    fun getPlongee(@Path("id") id: Int): Call<PlongeeModel>

    @PUT("dives/{id}")
    fun putPlongee(@Path("id") id: Int, @Body plongee: PlongeeModel): Call<Unit>

    @POST("dives")
    fun postPlongee(@Body plongee: PlongeeModel): Call<PlongeeModel>
}

class PlongeeClient {

    private val baseUrl = "https://dev-restandroid.users.info.unicaen.fr/api/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getPlongees(plongeeList: MutableState<MutableList<PlongeeModel>>){
        val apiService = retrofit.create(PlongeeApi::class.java)
        val call = apiService.getPlongees()
        val response = call.execute();
        if(response.isSuccessful){
            plongeeList.value= response.body()?.toMutableList()!!
        }else{
            Log.e("Main","Error on getPlongees request"+ (response.errorBody()?.string() ))
        }
    }

    fun getPlongee(id: Int, adherent: MutableState<PlongeeModel>) {

        val apiService = retrofit.create(PlongeeApi::class.java)
        val call = apiService.getPlongee(id)
        Log.println(Log.INFO,"main","ici")
        val response = call.execute();
        if(response.isSuccessful){
            adherent.value= response.body()!!
        }else{

            Log.e("Main","Error on getAdherent request"+ (response.message() ))
        }
    }

    fun putAdherent(plongee: PlongeeModel) {
        val apiService = retrofit.create(PlongeeApi::class.java)
        val call = apiService.putPlongee(plongee.id, plongee)
        call.execute()
    }

    fun postAdherent(plongee: PlongeeModel): PlongeeModel {
        val apiService = retrofit.create(PlongeeApi::class.java)
        val call = apiService.postPlongee(plongee)
        val response = call.execute()
        return if (response.isSuccessful) {
            response.body()!!
        } else {
            throw Exception("Error creating adherent")
        }
    }
}