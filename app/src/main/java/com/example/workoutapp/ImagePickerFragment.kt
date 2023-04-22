package com.example.workoutapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.workoutapp.databinding.ImageSelectorLayoutBinding


class ImagePickerFragment : Fragment() {

    private var _binding : ImageSelectorLayoutBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = ImageSelectorLayoutBinding.inflate(inflater, container, false)

        binding.exercise1.setOnClickListener {
            findNavController().navigate(R.id.action_imagePickerFragment2_to_addWorkoutFragment,
            bundleOf("drawable" to binding.exercise1.tag.toString()))

        }
        binding.exercise2.setOnClickListener {
            findNavController().navigate(R.id.action_imagePickerFragment2_to_addWorkoutFragment,
                bundleOf("drawable" to binding.exercise2.tag.toString()))

        }
        binding.exercise3.setOnClickListener {
            findNavController().navigate(R.id.action_imagePickerFragment2_to_addWorkoutFragment,
                bundleOf("drawable" to binding.exercise3.tag.toString()))

        }
        binding.exercise4.setOnClickListener {
            findNavController().navigate(R.id.action_imagePickerFragment2_to_addWorkoutFragment,
                bundleOf("drawable" to binding.exercise4.tag.toString()))

        }
        binding.exercise5.setOnClickListener {
            findNavController().navigate(R.id.action_imagePickerFragment2_to_addWorkoutFragment,
                bundleOf("drawable" to binding.exercise5.tag.toString()))

        }
        binding.exercise6.setOnClickListener {
            findNavController().navigate(R.id.action_imagePickerFragment2_to_addWorkoutFragment,
                bundleOf("drawable" to binding.exercise6.tag.toString()))

        }
        binding.exercise7.setOnClickListener {
            findNavController().navigate(R.id.action_imagePickerFragment2_to_addWorkoutFragment,
                bundleOf("drawable" to binding.exercise7.tag.toString()))

        }
        binding.exercise8.setOnClickListener {
            findNavController().navigate(R.id.action_imagePickerFragment2_to_addWorkoutFragment,
                bundleOf("drawable" to binding.exercise8.tag.toString()))

        }
        binding.exercise9.setOnClickListener {
            findNavController().navigate(R.id.action_imagePickerFragment2_to_addWorkoutFragment,
                bundleOf("drawable" to binding.exercise9.tag.toString()))

        }
        binding.exercise10.setOnClickListener {
            findNavController().navigate(R.id.action_imagePickerFragment2_to_addWorkoutFragment,
                bundleOf("drawable" to binding.exercise10.tag.toString()))

        }
        binding.exercise11.setOnClickListener {
            findNavController().navigate(R.id.action_imagePickerFragment2_to_addWorkoutFragment,
                bundleOf("drawable" to binding.exercise11.tag.toString()))

        }
        binding.exercise12.setOnClickListener {
            findNavController().navigate(R.id.action_imagePickerFragment2_to_addWorkoutFragment,
                bundleOf("drawable" to binding.exercise12.tag.toString()))

        }

        return binding.root
    }
}