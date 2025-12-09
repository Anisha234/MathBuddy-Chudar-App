package com.aachudar.chudarapp.data

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface WolframApi {
    @GET("v1/result")
    suspend fun getShortAnswer(
        @Query("i") query: String,
        @Query("appid") appId: String
    ): Response<ResponseBody>
}
