package com.confectinary.app.fragments.confectionary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.confectinary.app.db.AppDatabase
import com.confectinary.app.db.entity.ClientDb
import com.confectinary.app.db.entity.ConfectionaryDb
import com.confectinary.app.db.entity.ConfectionerDb
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class ConfectionaryViewModel (
    val db: AppDatabase?
): ViewModel() {
    private val _dataFlow = MutableSharedFlow<List<ConfectionaryDb>>()
    val dataFlow = _dataFlow.asSharedFlow()

    fun loadConfectionaries() {
        viewModelScope.launch {
            _dataFlow.emit(db?.getConfectionaryDao()?.getConfectionaries() ?: emptyList())
        }
    }

    fun insert(newItem: ConfectionaryDb){
        viewModelScope.launch {
            db?.getConfectionaryDao()?.insertConfectionary(newItem)
        }
        loadConfectionaries()
    }


}