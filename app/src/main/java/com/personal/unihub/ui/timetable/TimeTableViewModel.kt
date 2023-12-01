package com.personal.unihub.ui.timetable

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TimeTableViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is time table Fragment"
    }
    val text: LiveData<String> = _text
}
