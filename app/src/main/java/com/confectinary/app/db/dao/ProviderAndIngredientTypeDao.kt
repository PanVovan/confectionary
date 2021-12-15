package com.confectinary.app.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.confectinary.app.db.entity.relation.provider_with_confectionary.ProviderAndConfectionaryCrossRef
import com.confectinary.app.db.entity.relation.provider_with_ingredient.ProviderAndIngredientTypeCrossRef

@Dao
interface ProviderAndIngredientTypeDao {

    @Query("SELECT * FROM provider_and_ingredient")
    suspend fun getJunction(): List<ProviderAndIngredientTypeCrossRef>

    @Query("SELECT * FROM provider_and_ingredient WHERE ingredient_type_id = :ingredientType")
    suspend fun getProvidersByIngredientType(ingredientType: Int): List<ProviderAndIngredientTypeCrossRef>

    @Query("SELECT * FROM provider_and_ingredient WHERE provider_id = :providerId")
    suspend fun getIngredientTypesByProvider(providerId: Int): List<ProviderAndIngredientTypeCrossRef>

    @Insert
    suspend fun insertJunction(junction: ProviderAndIngredientTypeCrossRef)

    @Delete
    suspend fun deleteJunction(junction: ProviderAndIngredientTypeCrossRef)
}