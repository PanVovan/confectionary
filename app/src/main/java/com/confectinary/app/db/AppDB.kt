package com.confectinary.app.db

import android.content.Context
import androidx.room.Room


object AppDB {
    private var database: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase? {

        return if (database != null) {
            database
        } else {
            database = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "confectionary_database.db"
            ).build()
            database
        }
    }
}