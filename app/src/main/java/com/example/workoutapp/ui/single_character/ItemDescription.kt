package com.example.workoutapp.ui.single_character

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.workoutapp.R
import com.example.workoutapp.data.model.Exercise_Item
import com.example.workoutapp.databinding.FragmentItemDescriptionBinding
import com.example.workoutapp.ui.ItemsViewModel
import dagger.hilt.android.AndroidEntryPoint


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

        viewModel.chosenItem.observe(viewLifecycleOwner){


            val NumericPhoto : Int? = it.photo?.toIntOrNull()
            if(NumericPhoto == null) {
                Glide.with(binding.root).load(it.photo).into(binding.ditImage)
            }
            else{
                Glide.with(binding.root).load(NumericPhoto).into(binding.ditImage)
            }
            binding.ditTitle.text = it.title
            binding.ditDescription.text = it.description



        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
