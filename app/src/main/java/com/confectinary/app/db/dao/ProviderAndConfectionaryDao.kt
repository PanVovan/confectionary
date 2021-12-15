package com.confectinary.app.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.confectinary.app.db.entity.relation.provider_with_confectionary.ProviderAndConfectionaryCrossRef
import com.confectinary.app.db.entity.relation.provider_with_ingredient.ProviderAndIngredientTypeCrossRef

@Dao
interface ProviderAndConfectionaryDao {

    @Query("SELECT * FROM provider_and_confectionary")
    suspend fun getJunction(): List<ProviderAndConfectionaryCrossRef>

    @Query("SELECT * FROM provider_and_confectionary WHERE confectionary_id = :confectionaryId")
    suspend fun getProvidersByConfectionary(confectionaryId: Long): List<ProviderAndConfectionaryCrossRef>

    @Query("SELECT * FROM provider_and_confectionary WHERE provider_id = :providerId")
    suspend fun getConfectionariesByProvider(providerId: Long): List<ProviderAndConfectionaryCrossRef>

    @Insert
    suspend fun insertJunction(junction: ProviderAndConfectionaryCrossRef)

    @Delete
    suspend fun deleteJunction(junction: ProviderAndConfectionaryCrossRef)
}