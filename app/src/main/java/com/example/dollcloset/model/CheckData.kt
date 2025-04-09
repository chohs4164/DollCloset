package com.example.dollcloset.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class CheckData(
    isChecked: Boolean=false,
    val title: String,
    val imageResourceId: Int
) {
    var isChecked by mutableStateOf(isChecked)
}
