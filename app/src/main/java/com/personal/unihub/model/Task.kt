package com.personal.unihub.model

data class Task(
    val title: String,
    val content: String,
    val category: String,
    val timestamp: String,
)

data class TaskList(val tasks: List<Task>)