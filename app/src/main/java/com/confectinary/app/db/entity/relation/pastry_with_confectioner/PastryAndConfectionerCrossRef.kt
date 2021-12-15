package com.confectinary.app.db.entity.relation.pastry_with_confectioner

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "pastry_and_confectioner",
    primaryKeys = ["confectioner_id", "pastry_id"]
)
data class PastryAndConfectionerCrossRef(
    @ColumnInfo(name = "confectioner_id")
    val confectionerId: Long,
    @ColumnInfo(name = "pastry_id")
    val pastryId: Long
)
