package com.example.s3aplicmovi.composables

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import com.example.s3aplicmovi.entities.Pokemon
import com.example.s3aplicmovi.services.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun ListaPokemon() {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = retrofit.create(ApiService::class.java)

    var isLoading by remember { mutableStateOf(true) }
    var hasNetworkError by remember { mutableStateOf(false) }
    var pokemons by remember { mutableStateOf(listOf<Pokemon>()) }

    LaunchedEffect(Unit) {
        try {
            isLoading = true
            hasNetworkError = false
            pokemons = apiService.getPokemonList().results
        } catch (e: Exception) {
            hasNetworkError = true
            Log.e("MAIN_APP", e.toString())
        } finally {
            isLoading = false
        }
    }

    if (hasNetworkError) {
        Text(text = "No se ha podido conectar con la API de Pokémon")
    } else {
        if (isLoading) {
            Box(
                modifier = androidx.compose.ui.Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            Column {
                Button(
                    onClick = {
                        pokemons = pokemons + Pokemon("Nuevo Pokemon", "https://pokeapi.co/api/v2/pokemon/0/")
                    }
                ) {
                    Text(text = "Add Random Pokemon")
                }

                LazyColumn(
                    modifier = androidx.compose.ui.Modifier.fillMaxWidth()
                ) {
                    items(pokemons.size) { index ->
                        val pokemon = pokemons[index]
                        Column {
                            Text(
                                text = pokemon.name,
                            )
                            Text(
                                text = pokemon.url
                            )
                        }
                        HorizontalDivider(
                            androidx.compose.ui.Modifier,
                            DividerDefaults.Thickness,
                            DividerDefaults.color
                        )
                    }
                }
            }
        }
    }
}
