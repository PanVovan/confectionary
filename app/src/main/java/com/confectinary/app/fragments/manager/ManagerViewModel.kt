package com.confectinary.app.fragments.manager

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.confectinary.app.db.AppDatabase
import com.confectinary.app.db.entity.ClientDb
import com.confectinary.app.db.entity.ConfectionaryDb
import com.confectinary.app.db.entity.ManagerDb
import com.confectinary.app.db.entity.relation.manager_with_confectionary.ConfectionaryAndManagerCrossRef
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class ManagerViewModel (
    val db: AppDatabase?
): ViewModel() {
    private val _dataFlow = MutableSharedFlow<List<ManagerDb>>()
    val dataFlow = _dataFlow.asSharedFlow()

    private val _dataFlow1 = MutableSharedFlow<List<ConfectionaryDb>>()
    val dataFlow1 = _dataFlow1.asSharedFlow()

    fun loadConfectionaries() {
        viewModelScope.launch {
            _dataFlow1.emit(db?.getConfectionaryDao()?.getConfectionaries() ?: emptyList())
        }
    }


    fun loadManagers() {
        viewModelScope.launch {
            _dataFlow.emit(db?.getManagerDao()?.getManagers() ?: emptyList())
        }
    }

    fun insert(newItem: ManagerDb, confectionaryId: Int){
        viewModelScope.launch {
            db?.getManagerDao()?.insertManager(newItem)
        }
        viewModelScope.launch {
            db?.getManagerDao()?.insertConfectionaryAndManagerCrossRef(
                ConfectionaryAndManagerCrossRef(newItem.managerId, confectionaryId)
            )
        }
        loadManagers()
    }
}