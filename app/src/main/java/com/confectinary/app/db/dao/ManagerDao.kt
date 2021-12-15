package com.confectinary.app.db.dao

import androidx.room.*
import com.confectinary.app.db.entity.ClientDb
import com.confectinary.app.db.entity.ManagerDb
import com.confectinary.app.db.entity.relation.manager_with_confectionary.ConfectionaryAndManagerCrossRef
import com.confectinary.app.db.entity.relation.manager_with_confectionary.ManagerWithConfectionariesDb

@Dao
interface ManagerDao {

    @Query("SELECT * FROM manager")
    suspend fun getManagers(): List<ManagerDb>

    @Query("SELECT * FROM manager WHERE manager_id = :managerId")
    suspend fun getManager(managerId: Int): ManagerDb

    @Transaction
    @Query("SELECT * FROM manager WHERE manager_id = :managerId")
    suspend fun getManagerWithConfectionaries(managerId: Int): ManagerWithConfectionariesDb

    @Transaction
    @Query("SELECT * FROM manager")
    suspend fun getManagersWithConfectionaries(): List<ManagerWithConfectionariesDb>

    @Delete
    suspend fun deleteManager(manager: ManagerDb)

    @Insert
    suspend fun insertManager(manager: ManagerDb): Long
}