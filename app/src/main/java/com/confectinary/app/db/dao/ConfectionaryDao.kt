package com.confectinary.app.db.dao

import androidx.room.*
import com.confectinary.app.db.entity.ConfectionaryDb
import com.confectinary.app.db.entity.relation.manager_with_confectionary.ConfectionaryWithManagersDb
import com.confectinary.app.db.entity.relation.provider_with_confectionary.ConfectionaryWithProvidersDb

@Dao
interface ConfectionaryDao {
    @Query("SELECT * FROM confectionary WHERE confectionary_id = :confectionaryId")
    suspend fun getConfectionary(confectionaryId: Int): ConfectionaryDb

    @Transaction
    @Query("SELECT * FROM confectionary WHERE confectionary_id = :confectionaryId")
    suspend fun getConfectionaryWithManagers(confectionaryId: Int): ConfectionaryWithManagersDb

    @Transaction
    @Query("SELECT * FROM confectionary WHERE confectionary_id = :confectionaryId")
    suspend fun getConfectionaryWithProviders(confectionaryId: Int): ConfectionaryWithProvidersDb

    @Query("SELECT * FROM confectionary ")
    suspend fun getConfectionaries(): List<ConfectionaryDb>

    @Transaction
    @Query("SELECT * FROM confectionary ")
    suspend fun getConfectionariesWithManagers(): List<ConfectionaryWithManagersDb>

    @Transaction
    @Query("SELECT * FROM confectionary ")
    suspend fun getConfectionariesWithProviders(): List<ConfectionaryWithProvidersDb>

    @Insert
    suspend fun insertConfectionary(confectionary: ConfectionaryDb): Long

    @Delete
    suspend fun deleteConfectionary(confectionary: ConfectionaryDb)

}