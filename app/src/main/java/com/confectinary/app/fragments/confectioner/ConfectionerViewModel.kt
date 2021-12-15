package com.confectinary.app.fragments.confectioner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.confectinary.app.db.AppDatabase
import com.confectinary.app.db.entity.ClientDb
import com.confectinary.app.db.entity.ConfectionaryDb
import com.confectinary.app.db.entity.ConfectionerDb
import com.confectinary.app.db.entity.ManagerDb
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class ConfectionerViewModel(
    val db: AppDatabase?
): ViewModel() {
    private val _dataFlow = MutableSharedFlow<List<ConfectionerDb>>()
    val dataFlow = _dataFlow.asSharedFlow()

    fun loadConfectioners() {
        viewModelScope.launch {
            _dataFlow.emit(db?.getConfectionerDao()?.getConfectioners() ?: emptyList())
            _dataFlow1.emit(db?.getConfectionaryDao()?.getConfectionaries() ?: emptyList())
        }
    }

    private val _dataFlow1 = MutableSharedFlow<List<ConfectionaryDb>>()
    val dataFlow1 = _dataFlow1.asSharedFlow()


    fun insert(newItem: ConfectionerDb){
        viewModelScope.launch {
            db?.getConfectionerDao()?.insertConfectioner(newItem)
        }
        loadConfectioners()
    }

    fun delete(item: ConfectionerDb){

        viewModelScope.launch {
            db?.getConfectionerDao()?.deleteConfectioner(item)
        }
        loadConfectioners()
    }
}