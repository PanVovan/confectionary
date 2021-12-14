package com.confectinary.app.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.confectinary.app.db.entity.*
import com.confectinary.app.db.entity.relation.manager_with_confectionary.ConfectionaryAndManagerCrossRef
import com.confectinary.app.db.entity.relation.pastry_with_confectioner.PastryAndConfectionerCrossRef
import com.confectinary.app.db.entity.relation.provider_with_confectionary.ProviderAndConfectionaryCrossRef
import com.confectinary.app.db.entity.relation.provider_with_ingredient.ProviderAndIngredientTypeCrossRef

@Database(
    entities = [
        ClientDb::class,
        ConfectionaryDb::class,
        ConfectionerDb::class,
        DiscountCardDb::class,
        IngredientTypeDb::class,
        ManagerDb::class,
        OrderDb::class,
        PastryDb::class,
        ProviderDb::class,
        RecipeDb::class,
        ConfectionaryAndManagerCrossRef::class,
        PastryAndConfectionerCrossRef::class,
        ProviderAndIngredientTypeCrossRef::class,
        ProviderAndConfectionaryCrossRef::class
    ],
    version = 1
)
abstract class AppDatabase: RoomDatabase(), ConfectionaryDatabase {
}