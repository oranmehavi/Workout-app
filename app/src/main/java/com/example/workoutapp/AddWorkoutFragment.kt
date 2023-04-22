package com.example.workoutapp

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.workoutapp.databinding.AddWorkoutLayoutBinding


class AddWorkoutFragment : Fragment() {

    private var _binding : AddWorkoutLayoutBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = AddWorkoutLayoutBinding.inflate(inflater, container, false)

        arguments?.let {
            val resourceId = resources.getIdentifier(it.getString("drawable"), "drawable",
                requireContext().packageName);
            binding.resultImage.setImageDrawable(resources.getDrawable(resourceId))
        }
        binding.finishBtn.setOnClickListener {
            val item = Workout_Item(binding.workoutTitle.text.toString(), binding.workoutDescription.text.toString() ,binding.resultImage.drawable)
            WorkoutManager.add(item)

            findNavController().navigate(R.id.action_addWorkoutFragment_to_allWorkoutsFragment)
        }

        binding.imageBtn.setOnClickListener {
            findNavController().navigate(R.id.action_addWorkoutFragment_to_imagePickerFragment2)
        }

        return binding.root
    }
    //TODO: Change from _binding to liveData
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}