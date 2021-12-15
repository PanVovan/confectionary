package com.confectinary.app.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.confectinary.app.db.entity.relation.manager_with_confectionary.ConfectionaryAndManagerCrossRef
import com.confectinary.app.db.entity.relation.pastry_with_confectioner.PastryAndConfectionerCrossRef

@Dao
interface ConfectionaryAndManagerDao {

    @Query("SELECT * FROM confectionary_and_manager")
    suspend fun getJunction(): List<ConfectionaryAndManagerCrossRef>

    @Query("SELECT * FROM confectionary_and_manager WHERE confectionary_id = :confectionaryId")
    suspend fun getManagersByConfectionary(confectionaryId: Int): List<ConfectionaryAndManagerCrossRef>

    @Query("SELECT * FROM confectionary_and_manager WHERE manager_id = :managerId")
    suspend fun getConfectionariesByManager(managerId: Int): List<ConfectionaryAndManagerCrossRef>

    @Insert
    suspend fun insertJunction(junction: ConfectionaryAndManagerCrossRef)

    @Delete
    suspend fun deleteJunction(junction: ConfectionaryAndManagerCrossRef)
}