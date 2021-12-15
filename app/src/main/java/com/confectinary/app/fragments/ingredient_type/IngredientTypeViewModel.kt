package com.confectinary.app.fragments.ingredient_type

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.confectinary.app.db.AppDatabase
import com.confectinary.app.db.entity.IngredientTypeDb
import com.confectinary.app.db.entity.ProviderDb
import com.confectinary.app.db.entity.relation.provider_with_ingredient.ProviderAndIngredientTypeCrossRef
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class IngredientTypeViewModel(
    val db: AppDatabase?
): ViewModel() {
    private val _dataFlow = MutableSharedFlow<List<IngredientTypeDb>>()
    val dataFlow = _dataFlow.asSharedFlow()



    fun loadIngredientTypes() {
        viewModelScope.launch {
            _dataFlow.emit(db?.getIngredientTypeDao()?.getIngredientTypes() ?: emptyList())
        }
    }

    fun insert(newItem: IngredientTypeDb){
        viewModelScope.launch {
            db?.getIngredientTypeDao()?.insertIngredientType(newItem)
        }
        loadIngredientTypes()
    }


}