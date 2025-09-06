package com.example.s3aplicmovi.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.s3aplicmovi.R
import com.example.s3aplicmovi.entities.Pokemon

class PokemonAdapter(val data: List<Pokemon>) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        // este metodo define qué layout se va a usar
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_concat, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        // se comporta como una actividad
        val view = holder.itemView
        val item = data[position]
        val tvName = view.findViewById<TextView>(R.id.tvName)
        val tvUrl = view.findViewById<TextView>(R.id.tvPhone) // reutilizamos el segundo TextView

        tvName.text = item.name
        tvUrl.text = item.url

        view.setOnClickListener {
            Toast.makeText(view.context, "Seleccionaste a ${item.name}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) { }
}
