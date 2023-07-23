package com.example.svgassignment.ui.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.svgassignment.R
import com.example.svgassignment.databinding.FragmentGenerateImageBinding
import com.example.svgassignment.databinding.FragmentRecentlyGeneratedDogsBinding
import com.example.svgassignment.ui.adapters.DogImageAdapter
import com.example.svgassignment.ui.viewmodel.RecentlyGeneratedDogsViewModel
import com.example.svgassignment.utilities.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecentlyGeneratedDogsFragment : Fragment() {
    private val viewModel: RecentlyGeneratedDogsViewModel by viewModels()
    private var _binding: FragmentRecentlyGeneratedDogsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRecentlyGeneratedDogsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.clearCaches.setOnClickListener {
            viewModel.clearDogCache()
        }


        val adapter = DogImageAdapter()
        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerView.adapter = adapter
        System.out.println("cachetesting")
        viewModel.dogImagesLiveData.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> {
                    resource.data?.let { adapter.submitList(it) }
                }
                is Resource.Error -> {
                    // Handle error here using resource.message
                }
                is Resource.Loading -> {
                    // Show loading progress if required
                }
            }
        }

        viewModel.loadDogImages()
    }

}