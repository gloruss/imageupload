package com.example.imageuploadtest.utils

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody

const val JSON_SERVICE_UNAVAILABLE = "{\"status\":500,\"message\":\"Service Unavailable\"}"

fun String.fromJsonToResponseBody(): ResponseBody {
    val mediaType = "application/json".toMediaTypeOrNull()
    return toResponseBody(mediaType)
}