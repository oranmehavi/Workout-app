package com.example.workoutapp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.workoutapp.R
import com.example.workoutapp.databinding.PictureSelectBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PictureSelectFragment @Inject constructor(): Fragment() {

    private var _binding : PictureSelectBinding? = null
    private val binding get() = _binding!!


    private val pickImageLauncher: ActivityResultLauncher<Array<String>> =
        registerForActivityResult(ActivityResultContracts.OpenDocument()){
            binding.imageBtn9.setImageURI(it)
            requireActivity().contentResolver.takePersistableUriPermission(it!!, Intent.FLAG_GRANT_READ_URI_PERMISSION)
            viewModel.photoURI = it
        }


    private val viewModel: ItemsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PictureSelectBinding.inflate(inflater, container, false)

        //Listeners starting from picture button 1 - 8 and then a custom button

        binding.imageBtn1.setOnClickListener {
            photoSelectNotifier(1)
            viewModel.photoIndex = 1
        }
        binding.imageBtn2.setOnClickListener {
            photoSelectNotifier(2)
            viewModel.photoIndex = 2

        }
        binding.imageBtn3.setOnClickListener {
            photoSelectNotifier(3)
            viewModel.photoIndex = 3

        }
        binding.imageBtn4.setOnClickListener {
            photoSelectNotifier(4)
            viewModel.photoIndex = 4
        }
        binding.imageBtn5.setOnClickListener {
            photoSelectNotifier(5)
            viewModel.photoIndex = 5
        }
        binding.imageBtn6.setOnClickListener {
            photoSelectNotifier(6)
            viewModel.photoIndex = 6
        }
        binding.imageBtn7.setOnClickListener {
            photoSelectNotifier(7)
            viewModel.photoIndex = 7
        }
        binding.imageBtn8.setOnClickListener {
            photoSelectNotifier(8)
            viewModel.photoIndex = 8
        }
        //custom one
        binding.imageBtn9.setOnClickListener {
            //photoSelectNotifier(9)
            viewModel.photoIndex = 9
            pickImageLauncher.launch(arrayOf("image/*"))
            binding.imageBtn9.setImageURI(viewModel.photoURI)

        }

        binding.returnBtn.setOnClickListener {
            //activity?.supportFragmentManager?.popBackStack() failed try
            //findNavController().navigate(R.id.action_pictureSelectFragment_to_addWorkoutFragment, bundleOf("URI" to imageUri))
            findNavController().popBackStack()
        }

        return binding.root
    }


    private fun photoSelectNotifier(index : Int){
        Toast.makeText(requireContext(), "Photo number : $index was selected", Toast.LENGTH_SHORT).show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}