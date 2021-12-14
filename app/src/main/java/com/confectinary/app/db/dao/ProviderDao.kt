package com.confectinary.app.db.dao

import androidx.room.*
import com.confectinary.app.db.entity.ClientDb
import com.confectinary.app.db.entity.ProviderDb
import com.confectinary.app.db.entity.relation.provider_with_confectionary.ProviderWithConfectionariesDb
import com.confectinary.app.db.entity.relation.provider_with_ingredient.ProviderWithIngredientTypesDb
import java.security.Provider

@Dao
interface ProviderDao {
    @Query("SELECT * FROM provider")
    suspend fun getProviders(): List<ProviderDb>

    @Transaction
    @Query("SELECT * FROM provider")
    suspend fun getProvidersWithConfectionaries(): List<ProviderWithConfectionariesDb>

    @Transaction
    @Query("SELECT * FROM provider")
    suspend fun getProvidersWithIngredientTypes(): List<ProviderWithIngredientTypesDb>


    @Query("SELECT * FROM provider WHERE provider_id = :providerId")
    suspend fun getProvider(providerId: Int): ProviderDb

    @Transaction
    @Query("SELECT * FROM provider WHERE provider_id = :providerId")
    suspend fun getProviderWithConfectionaries(providerId: Int): ProviderWithConfectionariesDb

    @Transaction
    @Query("SELECT * FROM provider WHERE provider_id = :providerId")
    suspend fun getProviderWithIngredientTypes(providerId: Int): ProviderWithIngredientTypesDb

    @Insert
    suspend fun insertProvider(provider: ProviderDb)

    @Delete
    suspend fun deleteProvider(provider: ProviderDb)
}