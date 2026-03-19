package com.example.wordlegame

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map

class PageViewModel : ViewModel() {

    private val _index = MutableLiveData<Int>()

    val text: LiveData<String> = _index.map { index ->
        "Hello world from section: $index"
    }

    fun setIndex(index: Int) {
        _index.value = index
    }
}