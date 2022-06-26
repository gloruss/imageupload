package com.example.imageuploadtest.domain.model

import android.net.Uri

data class Image(
    val uri : Uri,
    val link : String? = null,
    val isUploadStarted : Boolean = false,
    val isUploadCompleted : Boolean = false,
    val exception : String? = null)
