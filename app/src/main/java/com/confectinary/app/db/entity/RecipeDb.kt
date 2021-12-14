package com.confectinary.app.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "recipe",
    foreignKeys = [
        ForeignKey(
            entity = PastryDb::class,
            parentColumns = arrayOf("pastry_id"),
            childColumns = arrayOf("pastry_id")
        ),
        ForeignKey(
            entity = IngredientTypeDb::class,
            parentColumns = arrayOf("ingredient_type_id"),
            childColumns = arrayOf("ingredient_type_id")
        ),
    ]
)
data class RecipeDb(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "recipe_id")
    val recipeId: Int,

    @ColumnInfo(name = "pastry_id")
    val pastryId: Int,
    @ColumnInfo(name = "ingredient_type_id")
    val ingredientTypeId: Int,
    val quantity: Int,
)
