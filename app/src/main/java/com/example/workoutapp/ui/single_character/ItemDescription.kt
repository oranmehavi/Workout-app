package com.example.workoutapp.ui.single_character


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.workoutapp.databinding.FragmentItemDescriptionBinding
import com.example.workoutapp.ui.ItemsViewModel


class itemDescription : Fragment() {

    private var _binding : FragmentItemDescriptionBinding? = null
    private val binding get() = _binding!!


    private val viewModel: ItemsViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentItemDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.chosenItem.observe(viewLifecycleOwner){ it ->


            val NumericPhoto : Int? = it.photo?.toIntOrNull()
            if(NumericPhoto == null) {
                Glide.with(binding.root).load(it.photo).into(binding.ditImage)
            }
            else{
                Glide.with(binding.root).load(NumericPhoto).into(binding.ditImage)
            }
            binding.ditTitle.text = it.title
            binding.ditDescription.text = it.description
            viewModel.getWorkoutWithSets(it.workoutId)?.observe(viewLifecycleOwner) { element ->
                Log.d("adapter", element.exercises?.size.toString())
                val myExerciseListAdapter = ExerciseListAdapter(requireActivity(), element.exercises?.toTypedArray())
                binding.exerciseview.adapter = myExerciseListAdapter
            }

        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
