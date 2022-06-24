package com.example.imageuploadtest.data.repository

import android.net.Uri
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import java.io.File

interface ImageUploadRepository {

    suspend fun uploadImage(uri: Uri) : Flow<String>
}