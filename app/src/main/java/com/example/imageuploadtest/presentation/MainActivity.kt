package com.example.imageuploadtest.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.imageuploadtest.presentation.countries.CountriesViewModel
import com.example.imageuploadtest.presentation.countries.CountryScreen
import com.example.imageuploadtest.presentation.images.ImageGalleryScreen
import com.example.imageuploadtest.presentation.images.ImageGalleryViewModel
import com.example.imageuploadtest.ui.theme.ImageUploadTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            ImageUploadTestTheme {
                NavHost(navController = navController, startDestination = "country"  ){
                    composable("country"){
                        val countriesViewModel = hiltViewModel<CountriesViewModel>()
                        CountryScreen(countriesViewModel){
                            navController.navigate("gallery")
                        }
                    }
                    composable("gallery"){
                        val imageGalleryViewModel = hiltViewModel<ImageGalleryViewModel>()
                        ImageGalleryScreen(imageGalleryViewModel)
                    }
                }

            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ImageUploadTestTheme {
        Greeting("Android")
    }
}