@file:OptIn(ExperimentalMaterialApi::class)

package com.example.imageuploadtest.presentation.images

import android.annotation.SuppressLint
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.*
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.flowWithLifecycle
import coil.compose.AsyncImage
import com.example.imageuploadtest.domain.model.DataState
import com.example.imageuploadtest.domain.model.Image
import com.example.imageuploadtest.presentation.countries.ErrorMessage
import com.example.imageuploadtest.presentation.countries.Loader

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ImageGalleryScreen (
    imageGalleryViewModel: ImageGalleryViewModel
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    val lifecycleAwareFlow = remember(imageGalleryViewModel.imageFlow,lifecycleOwner){
        imageGalleryViewModel.imageFlow.flowWithLifecycle(lifecycleOwner.lifecycle)
    }

    val imageState by lifecycleAwareFlow.collectAsState(initial = imageGalleryViewModel.imageFlow.value)

    val launcher = rememberLauncherForActivityResult(
        contract = (ActivityResultContracts.GetMultipleContents()),
        onResult = { uri ->
            imageGalleryViewModel.addImageUri(uri)
        }
    )
    ImageGalleryContent(imageState = imageState, onAddImageClick = {
        launcher.launch("image/jpeg")

    }, onCardClick = {
        if(!it.isUploadStarted || !it.isUploadCompleted){
            imageGalleryViewModel.uploadImage(it)
        }

    }, onImageDelete = {
        imageGalleryViewModel.deleteImage(it)
    })
}

@Composable
fun ImageGalleryContent(
    imageState : DataState<List<Image>>,
    onAddImageClick : () -> Unit,
    onCardClick: (Image) -> Unit,
    onImageDelete : (Image) -> Unit
){
    Scaffold( backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBar(

            content = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = ("Select images to Upload"), fontSize = 24.sp)
                    IconButton(onClick = {onAddImageClick()}) {
                        Icon(Icons.Filled.Add, "Add image")
                    }
                }
            }
            )
        }){ values ->
        Column(modifier = Modifier.fillMaxSize()) {
            if(!imageState.data.isNullOrEmpty()){
                ImageList(list = imageState.data,onCardClick,onImageDelete)
            }
            else{
                ErrorMessage(message = "Click + to add images")
            }
        }
    }
}


@Composable
fun ImageList(list : List<Image>, onCardClick: (Image) -> Unit, onImageDelete : (Image) -> Unit){
    LazyVerticalGrid(columns = GridCells.Fixed(2)){
        items(list){
            ImageCell(image = it, onCardClick,onImageDelete)

        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageCell(image: Image, onCardClick: (Image) -> Unit,onImageDelete: (Image) -> Unit){
    val context = LocalContext.current
    val uriHandler = LocalUriHandler.current
    val clipboardManager: ClipboardManager = LocalClipboardManager.current
    val annotatedString = buildAnnotatedString {
        image.link?.let {
            link->
            append(link)
            addStyle(
                style = SpanStyle(
                    color = Color.Blue,
                    textDecoration = TextDecoration.Underline,

                    ),
                start = 0,
                end = link.length
            )
        }

    }

    Card( modifier = Modifier
        .padding(10.dp)
        .clickable {
            onCardClick(image)
        }, backgroundColor = Color.White) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    IconButton(onClick = {onCardClick(image)},) {
                        Icon(if(!image.isUploadCompleted)Icons.Filled.Send else Icons.Filled.Done , "Upload image")
                    }
                    IconButton(onClick = {onImageDelete(image)}, ) {
                        Icon(Icons.Filled.Delete, "Delete image")
                    }
                }

                AsyncImage(model = image.uri.toString(), contentDescription = "galleryImage", contentScale = ContentScale.Fit, modifier = Modifier
                    )
                if(image.exception != null){
                    Text(text = image.exception, modifier = Modifier.padding(4.dp))
                }
                else {
                    Text(text = annotatedString, modifier = Modifier
                        .combinedClickable(
                            onClick = {
                                image.link?.let {
                                    uriHandler.openUri(it)
                                }
                            },
                            onLongClick = {
                                image.link?.let {
                                    clipboardManager.setText(AnnotatedString(it))
                                    Toast
                                        .makeText(
                                            context,
                                            "URL copied to Clipboard",
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()
                                }
                            }
                        )
                        .padding(4.dp))
                }



        }
        if (image.isUploadStarted && !image.isUploadCompleted) {
            Loader(Modifier.padding(top = 48.dp))
        }

    }


}




