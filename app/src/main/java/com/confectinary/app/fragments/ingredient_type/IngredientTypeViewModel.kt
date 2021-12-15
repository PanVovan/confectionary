package com.confectinary.app.fragments.ingredient_type

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.confectinary.app.db.AppDatabase
import com.confectinary.app.db.entity.IngredientTypeDb
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

    fun delete(item: IngredientTypeDb){

        viewModelScope.launch {
            db?.getIngredientTypeDao()?.deleteIngredientType(item)
            val junction1 = item.ingredientTypeId?.let {
                db?.getProviderAndIngredientTypeDao()?.getProvidersByIngredientType(it)
            }
            junction1?.forEach {
                db?.getProviderAndIngredientTypeDao()?.deleteJunction(it)
            }

            val junction2 = item.ingredientTypeId?.let {
                db?.getPastryAndIngredientTypeDao()?.getPastriesByIngredientTye(it)
            }
            junction2?.forEach {
                db?.getPastryAndIngredientTypeDao()?.deleteJunction(it)
            }
        }
        loadIngredientTypes()
    }

}