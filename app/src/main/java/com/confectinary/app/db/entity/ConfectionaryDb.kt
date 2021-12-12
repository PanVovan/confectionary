package com.confectinary.app.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "confectionary"
)
data class ConfectionaryDb(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "confectionary_id")
    val confectionaryId: Int,
    val address: String,
    val income: Int,
)
