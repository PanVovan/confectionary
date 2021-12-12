package com.confectinary.app.db.entity.relation.provider_with_confectionary

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "provider_and_confectionary",
    primaryKeys = ["provider_id", "confectionary_id"]
)
data class ProviderAndConfectionaryCrossRef(
    @ColumnInfo(name = "provider_id")
    val providerId: Int,

    @ColumnInfo(name = "confectionary_id")
    val confectionaryId: Int,
)
