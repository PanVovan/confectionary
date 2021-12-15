package com.confectinary.app.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.confectinary.app.db.entity.ClientDb
import com.confectinary.app.db.entity.RecipeDb

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipe WHERE pastry_id = :pastryId")
    suspend fun getRecipesByPastryId(pastryId: Int): List<RecipeDb>

    @Query("SELECT * FROM recipe WHERE recipe_id = :recipeId")
    suspend fun getRecipe(recipeId: Int): RecipeDb

    @Insert
    suspend fun insertRecipe(recipe: RecipeDb): Long

    @Delete
    suspend fun deleteRecipe(recipe: RecipeDb)
}