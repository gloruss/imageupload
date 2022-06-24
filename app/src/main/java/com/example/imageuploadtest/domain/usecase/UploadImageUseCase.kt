package com.example.imageuploadtest.domain.usecase

import android.net.Uri
import com.example.imageuploadtest.data.repository.ImageUploadRepository
import com.example.imageuploadtest.domain.model.Country
import kotlinx.coroutines.flow.Flow
import java.io.File
import javax.inject.Inject

class UploadImageUseCase @Inject constructor(
   private val repository: ImageUploadRepository
) {
    suspend  operator fun invoke(uri: Uri ) : Flow<String> =
        repository.uploadImage(uri)
}