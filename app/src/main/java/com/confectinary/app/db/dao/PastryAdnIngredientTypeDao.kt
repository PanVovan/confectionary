package com.confectinary.app.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.confectinary.app.db.entity.relation.pastry_with_ingredient.PastryAndIngredientTypeCrossRef
import com.confectinary.app.db.entity.relation.provider_with_confectionary.ProviderAndConfectionaryCrossRef


@Dao
interface PastryAdnIngredientTypeDao {

    @Query("SELECT * FROM pastry_and_ingredient_type")
    suspend fun getJunction(): List<PastryAndIngredientTypeCrossRef>

    @Query("SELECT * FROM pastry_and_ingredient_type WHERE ingredient_type_id = :ingredientTypeId")
    suspend fun getPastriesByIngredientTye(ingredientTypeId: Long): List<PastryAndIngredientTypeCrossRef>

    @Query("SELECT * FROM pastry_and_ingredient_type WHERE pastry_id = :pastryId")
    suspend fun getIngredientTypesByPastry(pastryId: Long): List<PastryAndIngredientTypeCrossRef>

    @Insert
    suspend fun insertJunction(junction: PastryAndIngredientTypeCrossRef)

    @Delete
    suspend fun deleteJunction(junction: PastryAndIngredientTypeCrossRef)
}