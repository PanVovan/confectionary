package com.confectinary.app.fragments.client

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.confectinary.app.db.AppDatabase
import com.confectinary.app.db.entity.ClientDb
import com.confectinary.app.db.entity.ConfectionaryDb
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class ClientViewModel(
    val db: AppDatabase?
): ViewModel() {
    private val _dataFlow = MutableSharedFlow<List<ClientDb>>()
    val dataFlow = _dataFlow.asSharedFlow()

    fun loadClients() {
        viewModelScope.launch {
            _dataFlow.emit(db?.getClientDao()?.getClients() ?: emptyList())

        }
    }

    fun insert(newItem: ClientDb){
        viewModelScope.launch {
            db?.getClientDao()?.insertClient(newItem)
        }
        loadClients()
    }

}