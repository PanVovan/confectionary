package com.confectinary.app.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "manager"
)
data class ManagerDb(
    @PrimaryKey
    @ColumnInfo( name = "manager_id")
    val managerId: Long?= null,
    val firstname: String,
    val lastname: String,
    val patronymic: String?,

    @ColumnInfo(name = "phone_number")
    val phoneNumber: String,
    val salary: Int,
    val experience: Int,
    val sphere: String
)
