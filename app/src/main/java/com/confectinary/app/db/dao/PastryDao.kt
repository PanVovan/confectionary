package com.confectinary.app.db.dao

import androidx.room.*
import com.confectinary.app.db.entity.PastryDb
import com.confectinary.app.db.entity.relation.pastry_with_confectioner.PastryWithConfectionersDb

@Dao
interface PastryDao {

    @Query("SELECT * FROM pastry WHERE pastry_id = :pastryId")
    suspend fun getPastry(pastryId: Int) : PastryDb

    @Transaction
    @Query("SELECT * FROM pastry WHERE pastry_id = :pastryId")
    suspend fun getPastryWithConfectioner(pastryId: Int): PastryWithConfectionersDb

    @Transaction
    @Query("SELECT * FROM pastry")
    suspend fun getPastriesWithConfectioner(): List<PastryWithConfectionersDb>

    @Query("SELECT * FROM pastry")
    suspend fun getPastries(): List<PastryDb>

    @Insert
    suspend fun insertPastry(pastry: PastryDb)

    @Delete
    suspend fun deletePastry(pastry: PastryDb)

}