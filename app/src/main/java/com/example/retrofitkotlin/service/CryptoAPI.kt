package com.example.retrofitkotlin.service

import com.example.retrofitkotlin.model.CryptoModel
import retrofit2.Call
import retrofit2.http.GET

interface CryptoAPI {
    //https://github.com/atilsamancioglu/K21-JSONDataSet/blob/master/crypto.json
    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    fun getData(): Call<List<CryptoModel>>




}