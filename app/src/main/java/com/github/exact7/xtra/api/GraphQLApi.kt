package com.github.exact7.xtra.api

import com.github.exact7.xtra.model.gql.clip.ClipDataResponse
import com.google.gson.JsonArray
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

@JvmSuppressWildcards
interface GraphQLApi {

    @POST(".")
    suspend fun getClipData(@Body json: JsonArray): ClipDataResponse

    @POST(".")
    suspend fun followChannel(@Header("Authorization") token: String, @Body json: JsonArray): Response<ResponseBody>
}