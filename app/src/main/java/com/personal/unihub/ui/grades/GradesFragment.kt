package com.personal.unihub.ui.grades

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.personal.unihub.databinding.FragmentGradesBinding

class GradesFragment : Fragment() {

    private var _binding: FragmentGradesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val gradesViewModel =
            ViewModelProvider(this).get(GradesViewModel::class.java)

        _binding = FragmentGradesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textGrades
        gradesViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
