package com.confectinary.app.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "pastry"
)
data class PastryDb (
    @PrimaryKey
    @ColumnInfo(name = "pastry_id")
    val pastry_id: Int,
    val price: Int,
    val naming: String,
    val manufactured: String
)