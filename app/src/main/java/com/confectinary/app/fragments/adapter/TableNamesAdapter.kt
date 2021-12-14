package com.confectinary.app.fragments.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.confectinary.app.MainActivity
import com.confectinary.app.R
import com.confectinary.app.databinding.FragmentRoleBinding
import com.confectinary.app.fragments.adapter.entity.TableNames

class TableNameAdapter( private val onclick: (String) -> Unit ): RecyclerView.Adapter<TableNameAdapter.ViewHolder>() {

    var values = TableNames.adminTableNames

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_table_name, parent, false)
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.name.text = item
        holder.touchZone.setOnClickListener { onclick(item) }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: View) : RecyclerView.ViewHolder(binding) {
        val name: TextView = binding.findViewById(R.id.table_name)
        val touchZone: View = binding.findViewById(R.id.touch_zone)

        override fun toString(): String {
            return super.toString() + " '" + name.text + "'"
        }
    }

}