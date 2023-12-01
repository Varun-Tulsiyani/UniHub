package com.personal.unihub.ui.reminder

import androidx.fragment.app.Fragment

class ReminderFragment : Fragment() {
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
