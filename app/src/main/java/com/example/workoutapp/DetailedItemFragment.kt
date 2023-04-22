package com.example.workoutapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.workoutapp.databinding.DetailedItemLayoutBinding

class DetailedItemFragment : Fragment() {


    private var _binding : DetailedItemLayoutBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailedItemLayoutBinding.inflate(inflater, container, false)



        return binding.root
    }
}