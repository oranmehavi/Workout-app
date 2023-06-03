package com.example.workoutapp.ui.add_character

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.workoutapp.R
import com.example.workoutapp.data.model.Workout_Item
import com.example.workoutapp.databinding.AddWorkoutLayoutBinding
import com.example.workoutapp.ui.ItemsViewModel


class AddWorkoutFragment : Fragment() {

    private var _binding : AddWorkoutLayoutBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ItemsViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = AddWorkoutLayoutBinding.inflate(inflater, container, false)

        //var photoURI: Uri? = null
        if (viewModel.photoIndex == 9) {
            Glide.with(this).load(viewModel.photoURI).into(binding.resultImage)
        } else {
            binding.resultImage.setImageResource(viewModel.imageList[viewModel.photoIndex - 1])
        }

        binding.finishBtn.setOnClickListener {
            if (binding.workoutTitle.text?.isEmpty() != true && binding.workoutDesc.text?.isEmpty() != true) {
                val item = Workout_Item(
                    binding.workoutTitle.text.toString(),
                    if (viewModel.photoIndex != 9) viewModel.imageList[viewModel.photoIndex - 1].toString() else viewModel.photoURI?.toString(),
                    binding.workoutDesc.text.toString(),
                    binding.workoutRepeats.text.toString(),
                    binding.workoutWeight.text.toString(),
                )
                viewModel.addItem(item)
                findNavController().navigate(R.id.action_addWorkoutFragment_to_allWorkoutsFragment)
            }
            else{
                Toast.makeText(requireContext(), getString(R.string.empty_workout_warning), Toast.LENGTH_SHORT).show()
            }
        }

        binding.imageBtn.setOnClickListener {
            findNavController().navigate(R.id.action_addWorkoutFragment_to_pictureSelectFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

