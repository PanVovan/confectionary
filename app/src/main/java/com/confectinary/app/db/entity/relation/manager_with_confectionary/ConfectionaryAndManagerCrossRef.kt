package com.confectinary.app.db.entity.relation.manager_with_confectionary

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "confectionary_and_manager",
    primaryKeys = ["manager_id", "confectionary_id"]
)
data class ConfectionaryAndManagerCrossRef(
    @ColumnInfo(name = "manager_id")
    val managerId: Int,

    @ColumnInfo(name = "confectionary_id")
    val confectionaryId: Int,

)
