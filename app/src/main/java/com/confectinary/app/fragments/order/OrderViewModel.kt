package com.confectinary.app.fragments.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.confectinary.app.db.AppDatabase
import com.confectinary.app.db.entity.ClientDb
import com.confectinary.app.db.entity.ConfectionaryDb
import com.confectinary.app.db.entity.ManagerDb
import com.confectinary.app.db.entity.OrderDb
import com.confectinary.app.db.entity.relation.manager_with_confectionary.ConfectionaryAndManagerCrossRef
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class OrderViewModel(
    val db: AppDatabase?
): ViewModel() {
    private val _dataFlow = MutableSharedFlow<List<OrderDb>>()
    val dataFlow = _dataFlow.asSharedFlow()

    private val _dataFlow1 = MutableSharedFlow<List<ClientDb>>()
    val dataFlow1 = _dataFlow1.asSharedFlow()

    fun loadOrders() {
        viewModelScope.launch {
            _dataFlow.emit(db?.getOrderDao()?.getOrders() ?: emptyList())
            _dataFlow1.emit(db?.getClientDao()?.getClients() ?: emptyList())
        }
    }

    fun insert(newItem: OrderDb) {

        viewModelScope.launch {
            db?.getOrderDao()?.insertOrder(newItem)
        }
        loadOrders()
    }
}
