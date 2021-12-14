package com.confectinary.app.extentions

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

fun Fragment.createFactory(model: ViewModel): ViewModelProvider.Factory {
    return object : ViewModelProvider.Factory {
        override fun <K : ViewModel> create(modelClass: Class<K>): K {
            return model as K
        }
    }
}