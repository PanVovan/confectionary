package com.confectinary.app.fragments.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.confectinary.app.R
import com.confectinary.app.db.entity.ClientDb
import com.confectinary.app.db.entity.ManagerDb

class ManagersAdapter : RecyclerView.Adapter<ManagersAdapter.ViewHolder>() {

    var values = emptyList<ManagerDb>()

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
        val phoneNumber = item.phoneNumber
        val salary = item.salary
        val experience = item.experience
        val sphere = item.sphere


        val displayTest = "Имя: $firstname\n" +
                "Фамилия: $lastname\n" +
                "Отчество: $patronymic\n" +
                "Телефон: $phoneNumber" +
                "Зарплата: $salary" +
                "Опыт: $experience" +
                "Сферы: $sphere"

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