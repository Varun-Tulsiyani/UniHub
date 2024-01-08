package com.personal.unihub.ui.todo

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.personal.unihub.model.Task

@Composable
fun ToDoGrid(startToDoActivity: (Context, Task) -> Unit) {
    val context: Context = LocalContext.current

    ExtendedFloatingActionButton(onClick = { /*TODO*/ }) {
        Text(text = "Extended FAB")
    }

    FloatingActionButton(onClick = { /*TODO*/ }) {
        Icon(Icons.Filled.Add, "Floating action button.")
    }
}