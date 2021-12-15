package com.confectinary.app.fragments.order

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.confectinary.app.R
import com.confectinary.app.db.entity.OrderDb

class OrdersAdapter : RecyclerView.Adapter<OrdersAdapter.ViewHolder>() {

    var values = emptyList<OrderDb>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_talbeitem, parent, false)
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        val firstname = item.description

        val displayTest = "Описание: $firstname"

        holder.description.text = displayTest
        holder.deleteBtn.visibility = View.GONE
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: View) : RecyclerView.ViewHolder(binding) {
        val description: TextView = binding.findViewById(R.id.description)
        val deleteBtn: ImageButton = binding.findViewById(R.id.delete_btn)

        override fun toString(): String {
            return super.toString() + " '" + description.text + "'"
        }
    }
}