package com.confectinary.app.db

import com.confectinary.app.db.dao.*

interface ConfectionaryDatabase {
    fun getClientDao(): ClientDao
    fun getConfectionaryAndManagerDao(): ConfectionaryAndManagerDao
    fun getConfectionaryDao(): ConfectionaryDao
    fun getConfectionerDao(): ConfectionerDao
    fun getDiscountCardDao(): DiscountCardDao
    fun getIngredientTypeDao(): IngredientTypeDao
    fun getManagerDao(): ManagerDao
    fun getOrderDao(): OrderDao
    fun getPastryAndConfectionerDao(): PastryAndConfectionerDao
    fun getPastryDao(): PastryDao
    fun getProviderAndConfectionaryDao(): ProviderAndConfectionaryDao
    fun getProviderAndIngredientTypeDao(): ProviderAndIngredientTypeDao
    fun getProviderDao(): ProviderDao
    fun getRecipeDao(): RecipeDao
}