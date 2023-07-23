package com.example.svgassignment.ui.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.svgassignment.R
import com.example.svgassignment.databinding.FragmentGenerateImageBinding
import com.example.svgassignment.databinding.FragmentRecentlyGeneratedDogsBinding
import com.example.svgassignment.databinding.FragmentSelectionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectionFragment : Fragment() {
    private var _binding: FragmentSelectionBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSelectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.generateDogButton.setOnClickListener {
            findNavController().navigate(R.id.action_selectionFragment_to_generateImageFragment)
        }

        binding.recentlyGeneratedDogsButton.setOnClickListener {
            findNavController().navigate(R.id.action_selectionFragment_to_recentlyGeneratedDogsFragment)
        }
    }
}