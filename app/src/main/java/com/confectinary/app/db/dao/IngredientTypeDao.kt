package com.confectinary.app.db.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.confectinary.app.db.entity.IngredientTypeDb
import com.confectinary.app.db.entity.relation.provider_with_ingredient.IngredientTypesWithProvidersDb

interface IngredientTypeDao {
    @Query("SELECT * FROM ingredient_type WHERE ingredient_type_id = :ingredientTypeId")
    suspend fun getIngredientType(ingredientTypeId: Int) : IngredientTypeDb

    @Transaction
    @Query("SELECT * FROM ingredient_type WHERE ingredient_type_id = :ingredientTypeId")
    suspend fun getIngredientTypeWithProviders(ingredientTypeId: Int): IngredientTypesWithProvidersDb

    @Transaction
    @Query("SELECT * FROM ingredient_type")
    suspend fun getIngredientTypesWithProviders(): List<IngredientTypesWithProvidersDb>

    @Query("SELECT * FROM ingredient_type")
    suspend fun getIngredientTypes(): List<IngredientTypeDb>

    @Insert
    suspend fun insertIngredientType(ingredientType: IngredientTypeDb)

    @Delete
    suspend fun deleteIngredientType(ingredientType: IngredientTypeDb)
}