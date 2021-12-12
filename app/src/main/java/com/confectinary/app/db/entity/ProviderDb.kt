package com.confectinary.app.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "provider"
)
data class ProviderDb(
    @PrimaryKey
    @ColumnInfo(name = "provider_id")
    val providerId: Int,
    val naming: String,
)
