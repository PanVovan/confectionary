package com.confectinary.app.fragments.ingredient_type

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.confectinary.app.R
import com.confectinary.app.db.entity.ClientDb
import com.confectinary.app.db.entity.IngredientTypeDb
import com.confectinary.app.fragments.client.ClientsAdapter

class IngredientTypeAdapter : RecyclerView.Adapter<IngredientTypeAdapter.ViewHolder>() {

    var values = emptyList<IngredientTypeDb>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_talbeitem, parent, false)
        )

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        val firstname = item.naming

        val displayTest = "Наименование: $firstname"

        holder.description.text = displayTest
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: View) : RecyclerView.ViewHolder(binding) {
        val description: TextView = binding.findViewById(R.id.description)

        override fun toString(): String {
            return super.toString() + " '" + description.text + "'"
        }
    }

}