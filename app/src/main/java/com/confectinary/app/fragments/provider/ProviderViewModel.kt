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
import com.confectinary.app.db.entity.relation.provider_with_confectionary.ProviderAndConfectionaryCrossRef
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


    private val _dataFlow1 = MutableSharedFlow<List<IngredientTypeDb>>()
    val dataFlow1 = _dataFlow1.asSharedFlow()

    private val _dataFlow2 = MutableSharedFlow<List<ConfectionaryDb>>()
    val dataFlow2 = _dataFlow2.asSharedFlow()


    fun loadProviders() {
        viewModelScope.launch {
            _dataFlow.emit(db?.getProviderDao()?.getProviders() ?: emptyList())
            _dataFlow1.emit(db?.getIngredientTypeDao()?.getIngredientTypes() ?: emptyList())
            _dataFlow2.emit(db?.getConfectionaryDao()?.getConfectionaries() ?: emptyList())
        }
    }

    fun insert(newItem: ProviderDb, ingredientId: Long, confectionaryId: Long){
        viewModelScope.launch {
            val id = db?.getProviderDao()?.insertProvider(newItem)

            val junction = ProviderAndIngredientTypeCrossRef(
               id!!, ingredientId
            )
            db?.getProviderAndIngredientTypeDao()?.insertJunction(junction)
            db?.getProviderAndConfectionaryDao()?.insertJunction(
                ProviderAndConfectionaryCrossRef(id!!, confectionaryId)
            )
        }
        loadProviders()
    }


}