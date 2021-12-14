package com.confectinary.app.fragments.confectioner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.confectinary.app.R
import com.confectinary.app.db.entity.ConfectionerDb

class ConfectionersAdapter : RecyclerView.Adapter<ConfectionersAdapter.ViewHolder>() {

    var values = emptyList<ConfectionerDb>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_talbeitem, parent, false)
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        val firstname = item.firstname
        val lastname = item.lastname
        val patronymic = item.patronymic?:""
        val salary = item.salary
        val experience = item.experience
        val rating = item.rating

        val displayTest = "Имя: $firstname\n" +
                "Фамилия: $lastname\n" +
                "Отчество: $patronymic\n" +
                "Зарплата: $salary\n" +
                "Опыт: $experience\n" +
                "Рейтинг: $rating"

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