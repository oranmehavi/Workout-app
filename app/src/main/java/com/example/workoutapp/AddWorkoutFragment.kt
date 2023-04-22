package com.example.workoutapp

import android.app.Activity
import android.content.Intent
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

    private var imageUri: Uri? = null

    val pickImageLauncher: ActivityResultLauncher<Array<String>> =
        registerForActivityResult(ActivityResultContracts.OpenDocument()){
            binding.resultImage.setImageURI(it)
            requireActivity().contentResolver.takePersistableUriPermission(it!!, Intent.FLAG_GRANT_READ_URI_PERMISSION)
            imageUri = it
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = AddWorkoutLayoutBinding.inflate(inflater, container, false)

        binding.finishBtn.setOnClickListener {
            val item = Workout_Item(binding.workoutTitle.text.toString(), imageUri.toString())
            WorkoutManager.add(item)

            findNavController().navigate(R.id.action_addWorkoutFragment_to_allWorkoutsFragment)
        }

        binding.imageBtn.setOnClickListener {
            pickImageLauncher.launch(arrayOf("image/*"))
        }

        return binding.root
    }
    //TODO: Change from _binding to liveData
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}