package com.confectinary.app.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "client",
)
data class ClientDb (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "client_id")
    val clientId: Int,
    val firstname: String,
    val lastname: String,
    val patronymic: String?,

    @ColumnInfo(name = "phone_number")
    val phoneNumber: String
)