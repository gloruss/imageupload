package com.example.imageuploadtest.presentation.countries

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.imageuploadtest.domain.model.Country
import com.example.imageuploadtest.domain.model.DataState

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun CountryScreen( countriesViewModel : CountriesViewModel, onCountryClicked : (Country) -> Unit ){

    val lifecycleOwner = LocalLifecycleOwner.current

    val lifecycleAwareFlow = remember(countriesViewModel.countryFlow,lifecycleOwner){
        countriesViewModel.countryFlow.flowWithLifecycle(lifecycleOwner.lifecycle)
    }

    val countryState by lifecycleAwareFlow.collectAsState(initial = countriesViewModel.countryFlow.value)
    CountryScreenContent(countryState = countryState,  ){
        onCountryClicked(it)
    }
}

@Composable
fun CountryScreenContent(
    countryState : DataState<List<Country>>,
    onCountryClicked: (Country) -> Unit
){
    Scaffold( backgroundColor = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBar(title = {
            Text(text = "Select your Country")
        }, )
        }) {it ->
        Column(modifier = Modifier.fillMaxSize()) {

            val data =  countryState.data
            if(data != null){
                CountryList(countryList = data, onCountryClicked)
            }

            if(countryState.loading){
                Loader(Modifier)
            }

            if(!countryState.exception.isNullOrEmpty()){
                ErrorMessage(message = countryState.exception)
            }


        }
    }
}


@Composable
fun CountryList(countryList : List<Country>,  onCountryClicked: (Country) -> Unit ){
    if(countryList.isNotEmpty()){
        val listState = rememberLazyListState()
        LazyColumn(state = listState){
            items(countryList){
                CountryRow(country = it,  onCountryClicked = onCountryClicked)
                Divider()
            }
        }


    }
    else{
        ErrorMessage(message = "Sorry , no beers found")
    }
}


@Composable
fun CountryRow( country: Country, onCountryClicked : (Country) -> Unit = {}){
    Card(

        modifier = Modifier
            .padding(10.dp)
            .clickable {
                onCountryClicked(country)
            }


    ){
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)) {
            Column(modifier = Modifier.weight(1f)
            ) {
                Text(text = country.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.size(5.dp))

            }

        }

    }
}


@Composable
fun ErrorMessage(message : String){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = message, fontSize = 20.sp
        )
    }
}


@Composable
fun Loader(modifier: Modifier ){
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,

    ) {
        CircularProgressIndicator(modifier = Modifier
            .width(200.dp)
            .height(200.dp)
            .semantics { contentDescription = "loader" },
        )
    }
}