package com.confectinary.app.db.entity.relation.pastry_with_ingredient

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "pastry_and_ingredient_type",
    primaryKeys = ["ingredient_type_id", "pastry_id"]
)
data class PastryAndIngredientTypeCrossRef(
    @ColumnInfo(name = "ingredient_type_id")
    val ingredientTypeId: Long,
    @ColumnInfo(name = "pastry_id")
    val pastryId: Long
)