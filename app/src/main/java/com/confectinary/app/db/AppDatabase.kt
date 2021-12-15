package com.confectinary.app.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.confectinary.app.db.dao.*
import com.confectinary.app.db.entity.*
import com.confectinary.app.db.entity.relation.manager_with_confectionary.ConfectionaryAndManagerCrossRef
import com.confectinary.app.db.entity.relation.pastry_with_confectioner.PastryAndConfectionerCrossRef
import com.confectinary.app.db.entity.relation.pastry_with_ingredient.PastryAndIngredientTypeCrossRef
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
        PastryAndIngredientTypeCrossRef::class,
        ProviderAndIngredientTypeCrossRef::class,
        ProviderAndConfectionaryCrossRef::class
    ],
    exportSchema = false,
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getClientDao(): ClientDao
    abstract fun getConfectionaryAndManagerDao(): ConfectionaryAndManagerDao
    abstract fun getConfectionaryDao(): ConfectionaryDao
    abstract fun getConfectionerDao(): ConfectionerDao
    abstract fun getDiscountCardDao(): DiscountCardDao
    abstract fun getIngredientTypeDao(): IngredientTypeDao
    abstract fun getManagerDao(): ManagerDao
    abstract fun getOrderDao(): OrderDao
    abstract fun getPastryAndConfectionerDao(): PastryAndConfectionerDao
    abstract fun getPastryAndIngredientTypeDao(): PastryAdnIngredientTypeDao
    abstract fun getPastryDao(): PastryDao
    abstract fun getProviderAndConfectionaryDao(): ProviderAndConfectionaryDao
    abstract fun getProviderAndIngredientTypeDao(): ProviderAndIngredientTypeDao
    abstract fun getProviderDao(): ProviderDao
    abstract fun getRecipeDao(): RecipeDao
}