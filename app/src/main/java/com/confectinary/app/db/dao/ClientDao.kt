package com.confectinary.app.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.confectinary.app.db.entity.ClientDb

@Dao
interface ClientDao {

    @Query("SELECT * FROM client")
    suspend fun getClients(): List<ClientDb>

    @Query("SELECT * FROM client WHERE client_id = :clientId")
    suspend fun getClient(clientId: Int): ClientDb

    @Insert
    suspend fun insertClient(client: ClientDb): Long

    @Delete
    suspend fun deleteClient(client: ClientDb)
}