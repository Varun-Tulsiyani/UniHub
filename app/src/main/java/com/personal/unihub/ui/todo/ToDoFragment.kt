package com.personal.unihub.ui.todo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.personal.unihub.model.Task

class ToDoFragment : Fragment() {

    private lateinit var startToDoActivity: (Context, Task) -> Unit

    companion object {
        fun newInstance(startToDoActivity: (Context, Task) -> Unit): ToDoFragment {
            val fragment = ToDoFragment()
            fragment.startToDoActivity = startToDoActivity
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Create a ComposeView and set its content
        val composeView = ComposeView(requireContext()).apply {
            setContent {
                ToDoGrid(startToDoActivity)
            }
        }

        // Return the ComposeView
        return composeView
    }

}
