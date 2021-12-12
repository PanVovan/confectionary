package com.confectinary.app.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "order",
    foreignKeys = [
        ForeignKey(
            entity = ClientDb::class,
            parentColumns = arrayOf("client_id"),
            childColumns = arrayOf("client_id")
        )
    ]
)
data class OrderDb(
    @PrimaryKey
    @ColumnInfo(name = "order_id")
    val orderId: Int,
    val description: String,
    @ColumnInfo(name = "client_id")
    val clientId: Int,
)
