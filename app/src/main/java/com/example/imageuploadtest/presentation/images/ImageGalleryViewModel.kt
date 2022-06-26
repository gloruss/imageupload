package com.example.imageuploadtest.presentation.images

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.imageuploadtest.domain.model.Country
import com.example.imageuploadtest.domain.model.DataState
import com.example.imageuploadtest.domain.model.Image
import com.example.imageuploadtest.domain.usecase.UploadImageUseCase
import com.example.imageuploadtest.presentation.countries.CountriesViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ImageGalleryViewModel @Inject constructor(
    private val useCase: UploadImageUseCase
): ViewModel() {

    private val _imageFlow = MutableStateFlow<DataState<List<Image>>>(DataState(emptyList()))
    val imageFlow : StateFlow<DataState<List<Image>>> = _imageFlow

    fun addImageUri( uriList: List<Uri>){
        _imageFlow.value = DataState(_imageFlow.value.data?.toMutableList()?.plus(uriList.map { Image(it) }))
    }

    fun deleteImage(image: Image){
        val temp = _imageFlow.value.data?.toMutableList()?.minus(image)
        _imageFlow.value = DataState(temp)
    }

    fun uploadImage(image: Image){
        val replaceList =  _imageFlow.value.data!!.toMutableList()
        val replaceIndex = replaceList.indexOfFirst { it.uri.toString() == image.uri.toString() }
            viewModelScope.launch {
                val tempList =   _imageFlow.value.data!!.toMutableList()
                tempList[tempList.indexOf(image)] = image.copy(isUploadStarted = true)
                _imageFlow.value = DataState(tempList)
            try {
                useCase.invoke(image.uri).collect{ link ->
                   // val replaceList =  _imageFlow.value.data!!.toMutableList()
                    replaceList[replaceIndex] = image.copy(link = link, isUploadCompleted = true, isUploadStarted = false, exception = null)
                _imageFlow.value = DataState(replaceList)

                }
            } catch (e: Exception) {
                Log.d("UploadError", e.message ?: "error")
                replaceList[replaceIndex] = image.copy(isUploadStarted = false, exception = e.message)
                _imageFlow.value = DataState(replaceList)
            }
        }
    }
}