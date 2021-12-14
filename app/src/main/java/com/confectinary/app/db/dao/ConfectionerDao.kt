package com.confectinary.app.db.dao

import androidx.room.*
import com.confectinary.app.db.entity.ConfectionerDb
import com.confectinary.app.db.entity.relation.pastry_with_confectioner.ConfectionerWithPastriesDb

@Dao
interface ConfectionerDao {
    @Query("SELECT * FROM confectioner WHERE confectioner_id = :confectionerId")
    suspend fun getConfectioner(confectionerId: Int): ConfectionerDb

    @Query("SELECT * FROM confectioner")
    suspend fun getConfectioners(): List<ConfectionerDb>

    @Transaction
    @Query("SELECT * FROM confectioner WHERE confectioner_id = :confectionerId")
    suspend fun getConfectionerWithPastries(confectionerId: Int): ConfectionerWithPastriesDb

    @Transaction
    @Query("SELECT * FROM confectioner")
    suspend fun getConfectionersWithPastries(): List<ConfectionerWithPastriesDb>

    @Delete
    suspend fun deleteConfectioner(confectioner: ConfectionerDb)

    @Insert
    suspend fun insertConfectioner(confectioner: ConfectionerDb)
}