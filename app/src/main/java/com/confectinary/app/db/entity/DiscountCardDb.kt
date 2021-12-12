package com.confectinary.app.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "discount_card",
    foreignKeys = [
        ForeignKey(
            entity = ClientDb::class,
            parentColumns = arrayOf("client_id"),
            childColumns = arrayOf("client_id")
        )
    ]
)
data class DiscountCardDb (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "card_id")
    val cardId: Int,
    val discount: Int,
    val registration: String,
    val type: String,
    @ColumnInfo(name = "client_id")
    val clientId: Int,
)