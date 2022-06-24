package com.example.imageuploadtest.data.network

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Url

interface ImageUploadApi {

    @POST
    @Multipart
   suspend fun uploadImage(@Url url : String = "https://catbox.moe/user/api.php",

    @Part file : MultipartBody.Part,@Part("reqtype") reqType : RequestBody,) : Response<String>
}