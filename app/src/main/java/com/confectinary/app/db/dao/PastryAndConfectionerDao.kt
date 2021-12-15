package com.confectinary.app.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.confectinary.app.db.entity.relation.manager_with_confectionary.ConfectionaryAndManagerCrossRef
import com.confectinary.app.db.entity.relation.pastry_with_confectioner.PastryAndConfectionerCrossRef
import com.confectinary.app.db.entity.relation.provider_with_confectionary.ProviderAndConfectionaryCrossRef

@Dao
interface PastryAndConfectionerDao {

    @Query("SELECT * FROM pastry_and_confectioner")
    suspend fun getJunction(): List<PastryAndConfectionerCrossRef>

    @Query("SELECT * FROM pastry_and_confectioner WHERE confectioner_id = :confectionerId")
    suspend fun getPastryByConfectioner(confectionerId: Int): List<PastryAndConfectionerCrossRef>

    @Query("SELECT * FROM pastry_and_confectioner WHERE pastry_id = :pastryId")
    suspend fun getConfectionersByPastry(pastryId: Int): List<PastryAndConfectionerCrossRef>

    @Insert
    suspend fun insertJunction(junction: PastryAndConfectionerCrossRef)

    @Delete
    suspend fun deleteJunction(junction: PastryAndConfectionerCrossRef)
}