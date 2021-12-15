package com.confectinary.app.fragments.pastry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.confectinary.app.db.AppDatabase
import com.confectinary.app.db.entity.*
import com.confectinary.app.db.entity.relation.manager_with_confectionary.ConfectionaryAndManagerCrossRef
import com.confectinary.app.db.entity.relation.pastry_with_ingredient.PastryAndIngredientTypeCrossRef
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class PastryViewModel(
    val db: AppDatabase?
): ViewModel() {
    private val _dataFlow = MutableSharedFlow<List<PastryDb>>()
    val dataFlow = _dataFlow.asSharedFlow()

    private val _dataFlow1 = MutableSharedFlow<List<IngredientTypeDb>>()
    val dataFlow1 = _dataFlow1.asSharedFlow()

    private val _dataFlow2 = MutableSharedFlow<List<OrderDb>>()
    val dataFlow2 = _dataFlow2.asSharedFlow()


    fun loadPastries() {
        viewModelScope.launch {
            _dataFlow.emit(db?.getPastryDao()?.getPastries() ?: emptyList())
            _dataFlow1.emit(db?.getIngredientTypeDao()?.getIngredientTypes() ?: emptyList())
            _dataFlow2.emit(db?.getOrderDao()?.getOrders() ?: emptyList())
        }
    }

    fun insert(newItem: PastryDb, ingredientTypeId1: Long?, ingredientTypeId2: Long?){

        viewModelScope.launch {
            val id = db?.getPastryDao()?.insertPastry(newItem)

            ingredientTypeId1?.let {
                db?.getPastryAndIngredientTypeDao()?.insertJunction(
                    PastryAndIngredientTypeCrossRef(id!!, it)
                )
            }
            ingredientTypeId2?.let {
                db?.getPastryAndIngredientTypeDao()?.insertJunction(
                    PastryAndIngredientTypeCrossRef(id!!, it)
                )
            }
        }
        loadPastries()
    }
}