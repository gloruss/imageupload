package com.example.imageuploadtest.data.repository

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.provider.OpenableColumns
import com.example.imageuploadtest.data.network.ImageUploadApi
import com.example.imageuploadtest.data.network.toNetworkError
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import javax.inject.Inject


class ImageUploadRepositoryImpl @Inject constructor(
    private val api : ImageUploadApi,
    @ApplicationContext private val  context: Context
) : ImageUploadRepository {


    override suspend fun uploadImage(uri : Uri): Flow<String> =
        flow{

            val stream = context.contentResolver.openInputStream(uri) ?: throw Exception("Can't upload this image")
            val bitmap = BitmapFactory.decodeStream(stream)
            val outputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50,outputStream)

            val request =
                outputStream.toByteArray().toRequestBody("image/*".toMediaTypeOrNull(), 0)

            val body: MultipartBody.Part = MultipartBody.Part.createFormData("fileToUpload", "test.jpg", request)

            val reqType: RequestBody =
                "fileupload".toRequestBody("multipart/form-data".toMediaTypeOrNull())

            api.uploadImage(reqType = reqType,file = body).let {
                response ->
                if(response.isSuccessful){
                    val response = response.body().toString()
                    emit(response ?: "No link provided")
                }
                else{
                     throw response.code().toNetworkError()
                }
            }
        }





    fun createFileFromContentUri(fileUri : Uri) : File{

        var fileName  = ""


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.contentResolver.query(fileUri,null,null,null)
            } else {
                TODO("VERSION.SDK_INT < O")

        }?.use { cursor ->
            val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            cursor.moveToFirst()
            fileName = cursor.getString(nameIndex)
        }

        //  For extract file mimeType
        val fileType: String? = fileUri.let { returnUri ->
            context.contentResolver.getType(returnUri)
        }

        val iStream : InputStream = context.contentResolver.openInputStream(fileUri)!!
        val outputDir : File = context?.cacheDir!!
        val outputFile : File = File(outputDir,fileName)
        copyStreamToFile(iStream, outputFile)
        iStream.close()
        return  outputFile
    }

    fun copyStreamToFile(inputStream: InputStream, outputFile: File) {
        inputStream.use { input ->
            val outputStream = FileOutputStream(outputFile)
            outputStream.use { output ->
                val buffer = ByteArray(4 * 1024) // buffer size
                while (true) {
                    val byteCount = input.read(buffer)
                    if (byteCount < 0) break
                    output.write(buffer, 0, byteCount)
                }
                output.flush()
            }
        }
    }

}