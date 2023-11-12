package com.example.and_101_project_5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class APODAdapter (
    private val countryNames: List<String>,
    private val countryFlags: List<String>,
    private val countryCoatOfArms: List<String>,
    private val countryPopulations: List<String>
    ) : RecyclerView.Adapter<APODAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        // create variables sending data to Views in RecyclerView
        val name : TextView
        val flag : ImageView
        val coatOfArms : ImageView
        val population : TextView

        // link variables to Views in "apod_items.xml"
        init {
            name = view.findViewById(R.id.name)
            flag = view.findViewById(R.id.flag)
            coatOfArms = view.findViewById(R.id.coatOfArms)
            population = view.findViewById(R.id.population)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.apod_items, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = countryNames.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // loads country names
        holder.name.text = countryNames[position]

        // loads country flags
        Glide.with(holder.itemView)
            .load(countryFlags[position])
            .centerCrop()
            .into(holder.flag)

        // loads country coat of arms
        Glide.with(holder.itemView)
            .load(countryCoatOfArms[position])
            .centerCrop()
            .into(holder.coatOfArms)

        // loads country populations
        holder.population.text = "Population: " + countryPopulations[position]
    }

}
