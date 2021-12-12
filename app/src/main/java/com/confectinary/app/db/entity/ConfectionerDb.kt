package com.confectinary.app.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "confectioner",
    foreignKeys = [
        ForeignKey(
            entity = ConfectionaryDb::class,
            parentColumns = arrayOf("confectionary_id"),
            childColumns = arrayOf("confectionary_id")
        )
    ]
)
data class ConfectionerDb(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "confectioner_id")
    val confectionerId: Int,

    val firstname: String,
    val lastname: String,
    val patronymic: String?,
    val salary: Int,
    val experience: Int,
    val rating: Int,

    @ColumnInfo(name = "confectionary_id")
    val confectionaryId: Int,
)
