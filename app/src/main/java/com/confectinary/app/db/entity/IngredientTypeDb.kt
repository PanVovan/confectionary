package com.confectinary.app.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "ingredient_type"
)
data class IngredientTypeDb(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ingredient_type_id")
    val ingredientTypeId: Int,
    val naming: Int,
)
