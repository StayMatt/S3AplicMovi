package com.example.s3aplicmovi.services

import com.example.s3aplicmovi.entities.Pokemon
import retrofit2.http.GET

interface ApiService {

    // GET https://pokeapi.co/api/v2/pokemon
    @GET("pokemon")
    suspend fun getPokemons(): List<Pokemon>

}
