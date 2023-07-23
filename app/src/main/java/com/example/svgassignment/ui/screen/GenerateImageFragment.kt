package com.example.svgassignment.ui.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.svgassignment.R
import com.example.svgassignment.databinding.FragmentGenerateImageBinding
import com.example.svgassignment.ui.viewmodel.GenerateDogsViewModel
import com.example.svgassignment.utilities.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GenerateImageFragment : Fragment() {
    private val viewModel: GenerateDogsViewModel by viewModels()
    private var _binding: FragmentGenerateImageBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentGenerateImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.generateDogs.setOnClickListener {
            viewModel.generateDogImage()
        }

        viewModel.dogImageLiveData.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> {
                    // Hide the loading progress if required
                    // Get the DogApiResponse from the Resource data
                    val dogApiResponse = resource.data

                    // Use the DogApiResponse to get the image URL
                    val imageUrl = dogApiResponse?.message

                    // Load and display the dog image into the ImageView using Glide
                    imageUrl?.let {
                        Glide.with(requireContext())
                            .load(it)
                            .into(binding.dogImage) // Replace imageView with the actual ImageView in your layout
                    }
                }
                is Resource.Error -> {
                    // Handle error here using resource.message
                    val errorMessage = resource.message
                    Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                    // Show loading progress if required
                }
            }
        }
    }

}