package com.example.imageuploadtest.domain.model

import android.net.Uri

data class Image(
    val uri : Uri,
    var link : String? = null,
    val isUploadStarted : Boolean = false,
    var isUploadCompleted : Boolean = false)
