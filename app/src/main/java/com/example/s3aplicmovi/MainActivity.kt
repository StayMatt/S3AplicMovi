package com.example.s3aplicmovi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.s3aplicmovi.adapters.PokemonAdapter
import com.example.s3aplicmovi.entities.Pokemon

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val pokemons = listOf(
            Pokemon("Bulbasaur", "https://pokeapi.co/api/v2/pokemon/1/"),
            Pokemon("Ivysaur", "https://pokeapi.co/api/v2/pokemon/2/"),
            Pokemon("Venusaur", "https://pokeapi.co/api/v2/pokemon/3/")
        )

        val rvPokemon = findViewById<RecyclerView>(R.id.rvContacts)
        rvPokemon.adapter = PokemonAdapter(pokemons)
        rvPokemon.setHasFixedSize(true)
        rvPokemon.layoutManager = LinearLayoutManager(this)
    }
}
