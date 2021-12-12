package com.confectinary.app.db.entity.relation.provider_with_ingredient

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "provider_and_ingredient",
    primaryKeys = ["provider_id", "ingredient_type_id"]
)
data class ProviderAndIngredientTypeCrossRef(
    @ColumnInfo(name = "provider_id")
    val providerId: Int,
    @ColumnInfo(name = "ingredient_type_id")
    val ingredientTypeId: Int,
)
