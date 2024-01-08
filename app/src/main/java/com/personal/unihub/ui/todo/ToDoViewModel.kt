package com.personal.unihub.ui.todo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ToDoViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply { value = "This is todo Fragment" }
    val text: LiveData<String> = _text
}
