package com.confectinary.app.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "order_accepted",
    foreignKeys = [
        ForeignKey(
            entity = OrderDb::class,
            parentColumns = arrayOf("order_id"),
            childColumns = arrayOf("order_id")
        ),
        ForeignKey(
            entity = ConfectionerDb::class,
            parentColumns = arrayOf("confectioner_id"),
            childColumns = arrayOf("confectioner_id")
        ),
        ForeignKey(
            entity = PastryDb::class,
            parentColumns = arrayOf("pastry_id"),
            childColumns = arrayOf("pastry_id")
        ),
    ],
    primaryKeys = ["order_id"]
)
data class OrderAcceptedDb(
    @ColumnInfo(name = "order_id")
    val orderId: Int,
    @ColumnInfo(name = "confectioner_id")
    val confectionerId: Int,
    @ColumnInfo(name = "pastry_id")
    val pastryId: Int,
)
