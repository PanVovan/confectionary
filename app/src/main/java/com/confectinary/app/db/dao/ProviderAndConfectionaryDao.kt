package com.confectinary.app.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.confectinary.app.db.entity.relation.provider_with_confectionary.ProviderAndConfectionaryCrossRef

@Dao
interface ProviderAndConfectionaryDao {

    @Query("SELECT * FROM provider_and_confectionary WHERE confectionary_id = :confectionaryId")
    suspend fun getProvidersByConfectionary(confectionaryId: Int): List<ProviderAndConfectionaryCrossRef>

    @Query("SELECT * FROM provider_and_confectionary WHERE provider_id = :providerId")
    suspend fun getConfectionariesByProvider(providerId: Int): List<ProviderAndConfectionaryCrossRef>

    @Insert
    suspend fun insertJunction(junction: ProviderAndConfectionaryCrossRef)

    @Delete
    suspend fun deleteJunction(junction: ProviderAndConfectionaryCrossRef)
}