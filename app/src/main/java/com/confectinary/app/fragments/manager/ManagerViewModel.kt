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

    fun loadManagers() {
        viewModelScope.launch {
            _dataFlow.emit(db?.getManagerDao()?.getManagers() ?: emptyList())
            _dataFlow1.emit(db?.getConfectionaryDao()?.getConfectionaries() ?: emptyList())
        }
    }

    fun insert(newItem: ManagerDb, confectionaryId: Long){

        viewModelScope.launch {
            val id = db?.getManagerDao()?.insertManager(newItem)
            db?.getConfectionaryAndManagerDao()?.insertJunction(
                ConfectionaryAndManagerCrossRef(id!!, confectionaryId)
            )
        }
        loadManagers()
    }

    fun delete(item: ManagerDb){

        viewModelScope.launch {
            db?.getManagerDao()?.deleteManager(item)
            val junction1 = item.managerId?.let {
                db?.getConfectionaryAndManagerDao()?.getConfectionariesByManager(it)
            }
            junction1?.forEach {
                db?.getConfectionaryAndManagerDao()?.deleteJunction(it)
            }
        }
        loadManagers()
    }
}