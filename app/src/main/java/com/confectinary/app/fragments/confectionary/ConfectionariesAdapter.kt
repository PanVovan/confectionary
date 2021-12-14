package com.confectinary.app.fragments.confectionary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.confectinary.app.R
import com.confectinary.app.db.entity.ConfectionaryDb

class ConfectionariesAdapter : RecyclerView.Adapter<ConfectionariesAdapter.ViewHolder>() {

    var values = listOf(
        ConfectionaryDb(0, "Волгоград, ул. Ленина, 21/1", 100000),
        ConfectionaryDb(0, "Тверь, ул. Перекопченко, 15", 214000),
        ConfectionaryDb(0, "Тверь, ул. Перекопченко, 15", 214000),
        ConfectionaryDb(0, "Тверь, ул. Перекопченко, 15", 214000),
        ConfectionaryDb(0, "Тверь, ул. Перекопченко, 15", 214000),
        ConfectionaryDb(0, "Тверь, ул. Перекопченко, 15", 214000),
        ConfectionaryDb(0, "Тверь, ул. Перекопченко, 15", 214000),
        ConfectionaryDb(0, "Тверь, ул. Перекопченко, 15", 214000),
        ConfectionaryDb(0, "Тверь, ул. Перекопченко, 15", 214000),
        ConfectionaryDb(0, "Тверь, ул. Перекопченко, 15", 214000),
        ConfectionaryDb(0, "Тверь, ул. Перекопченко, 15", 214000),
        ConfectionaryDb(0, "Тверь, ул. Перекопченко, 15", 214000),
        ConfectionaryDb(0, "Тверь, ул. Перекопченко, 15", 214000),
        ConfectionaryDb(0, "Тверь, ул. Перекопченко, 15", 214000),
        ConfectionaryDb(0, "Тверь, ул. Перекопченко, 15", 214000),
        ConfectionaryDb(0, "Тверь, ул. Перекопченко, 15", 214000),
        ConfectionaryDb(0, "Тверь, ул. Перекопченко, 15", 214000),
        ConfectionaryDb(0, "Тверь, ул. Перекопченко, 15", 214000),
        ConfectionaryDb(0, "Тверь, ул. Перекопченко, 15", 214000),
        ConfectionaryDb(0, "Тверь, ул. Перекопченко, 15", 214000)
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_talbeitem, parent, false)
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        val address = item.address
        val income = item.income
        val displayTest =
            "Адрес: $address\n"+
            "Доход: $income"

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