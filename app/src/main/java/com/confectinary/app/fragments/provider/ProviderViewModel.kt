package com.confectinary.app.fragments.provider

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Junction
import com.confectinary.app.db.AppDatabase
import com.confectinary.app.db.entity.ClientDb
import com.confectinary.app.db.entity.ConfectionaryDb
import com.confectinary.app.db.entity.IngredientTypeDb
import com.confectinary.app.db.entity.ProviderDb
import com.confectinary.app.db.entity.relation.provider_with_ingredient.ProviderAndIngredientTypeCrossRef
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.security.Provider

class ProviderViewModel(
    val db: AppDatabase?
): ViewModel() {
    private val _dataFlow = MutableSharedFlow<List<ProviderDb>>()
    val dataFlow = _dataFlow.asSharedFlow()

    var ingredientTypes: List<IngredientTypeDb> = emptyList()


    fun loadProviders() {
        viewModelScope.launch {
            _dataFlow.emit(db?.getProviderDao()?.getProviders() ?: emptyList())
            ingredientTypes = db?.getIngredientTypeDao()?.getIngredientTypes() ?: emptyList()
        }
    }

    fun insert(newItem: ProviderDb, ingredientId: Long){
        viewModelScope.launch {
            val id = db?.getProviderDao()?.insertProvider(newItem)

            val junction = ProviderAndIngredientTypeCrossRef(
               id!!, ingredientId
            )
            db?.getProviderAndIngredientTypeDao()?.insertJunction(junction)
        }
        loadProviders()
    }


}