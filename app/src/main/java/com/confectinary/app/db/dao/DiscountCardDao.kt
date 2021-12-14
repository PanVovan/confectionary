package com.confectinary.app.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.confectinary.app.db.entity.DiscountCardDb

@Dao
interface DiscountCardDao {

    @Query("SELECT * FROM discount_card WHERE client_id = :clientId")
    suspend fun getClientsDiscountCardById(clientId: Int): List<DiscountCardDb>

    @Query("SELECT * FROM discount_card WHERE card_id = :cardId")
    suspend fun getDiscountCardById(cardId: Int): DiscountCardDb

    @Query("DELETE FROM discount_card WHERE client_id = :clientId")
    suspend fun deleteDiscountCardAtClient(clientId: Int)

    @Insert
    suspend fun insertDiscountCard(card: DiscountCardDb)

    @Delete
    suspend fun deleteDiscountCard(card: DiscountCardDb)
}