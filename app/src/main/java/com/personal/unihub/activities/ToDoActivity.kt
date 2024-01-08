package com.personal.unihub.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.personal.unihub.model.Task

class ToDoActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = intent
        val title: String = intent.getStringExtra(KEY_TITLE)!!
        val content: String = intent.getStringExtra(KEY_CONTENT)!!
        val category: String = intent.getStringExtra(KEY_CATEGORY)!!
        val timestamp: String = intent.getStringExtra(KEY_TIMESTAMP)!!
        val task = Task(title, content, category, timestamp)
        setContent { TaskView(task) }
    }

    companion object {
        const val KEY_TITLE = "title"
        const val KEY_CONTENT = "content"
        const val KEY_CATEGORY = "category"
        const val KEY_TIMESTAMP = "timestamp"
    }
}
