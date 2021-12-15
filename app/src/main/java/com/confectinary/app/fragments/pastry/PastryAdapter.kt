package com.confectinary.app.fragments.pastry

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.ColumnInfo
import com.confectinary.app.R
import com.confectinary.app.db.entity.ConfectionerDb
import com.confectinary.app.db.entity.ManagerDb
import com.confectinary.app.db.entity.OrderDb
import com.confectinary.app.db.entity.PastryDb
import com.confectinary.app.fragments.manager.ManagersAdapter

class PastryAdapter : RecyclerView.Adapter<PastryAdapter.ViewHolder>() {

    var values = emptyList<PastryDb>()
    var orders = emptyList<OrderDb>()
    var confectioners = emptyList<ConfectionerDb>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_talbeitem, parent, false)
        )

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        val price = item.price
        val naming = item.naming
        val manufactured = item.manufactured
        val order = orders.filter { it.orderId == item.orderId }.getOrNull(0)

        var displayTest = "Наименование: $naming\n" +
                "Стоимость: $price\n" +
                "Дата изготовления: $manufactured"

        order?.let {
            displayTest = displayTest.plus("\nЗаказ: ${order.description}")
        }

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