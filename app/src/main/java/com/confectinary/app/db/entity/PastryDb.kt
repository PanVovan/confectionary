package com.confectinary.app.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "pastry",
    foreignKeys = [
        ForeignKey(
            entity = OrderDb::class,
            parentColumns = arrayOf("order_id"),
            childColumns = arrayOf("order_id")
        ),
    ]
)
data class PastryDb (
    @PrimaryKey
    @ColumnInfo(name = "pastry_id")
    val pastry_id: Long?= null,
    val price: Int,
    val naming: String,
    val manufactured: String,

    @ColumnInfo(name = "order_id")
    val orderId: Long,
)