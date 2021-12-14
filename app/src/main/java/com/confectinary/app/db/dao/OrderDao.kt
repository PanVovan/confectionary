package com.confectinary.app.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.confectinary.app.db.entity.OrderDb

@Dao
interface OrderDao {
    @Query("SELECT * FROM `order` WHERE order_id = :clientId")
    suspend fun getOrdersByClientId(clientId: Int): List<OrderDb>

    @Query("SELECT * FROM `order` WHERE order_id = :orderId")
    suspend fun getOrder(orderId: Int): OrderDb

    @Query("SELECT * FROM `order`s")
    suspend fun getOrders(): List<OrderDb>

    @Insert
    suspend fun insertOrder(order: OrderDb)

    @Delete
    suspend fun deleteOrder(order: OrderDb)
}