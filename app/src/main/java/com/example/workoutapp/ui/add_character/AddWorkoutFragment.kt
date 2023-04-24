package com.example.workoutapp.ui.add_character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
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

        binding.finishBtn.setOnClickListener {
            val item = Workout_Item(
                binding.workoutTitle.text.toString(),
                viewModel.imageList[viewModel.getIndex()],
                binding.workoutDesc.text.toString(),
                binding.workoutRepeats.text.toString(),
                binding.workoutWeight.text.toString(),
            )
            viewModel.addItem(item)
            findNavController().navigate(R.id.action_addWorkoutFragment_to_allWorkoutsFragment)
        }

        binding.imageBtn.setOnClickListener {
            viewModel.plusOneIndex()
            binding.resultImage.setImageResource(viewModel.imageList[viewModel.getIndex()])
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

