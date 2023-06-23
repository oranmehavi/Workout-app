package com.example.workoutapp.ui.all_characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workoutapp.R
import com.example.workoutapp.databinding.AllWorkoutLayoutBinding
import com.example.workoutapp.ui.ItemsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllWorkoutsFragment : Fragment() {

    private var _binding : AllWorkoutLayoutBinding? = null

    private val binding get() = _binding!!

    private val viewModel: ItemsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        _binding = AllWorkoutLayoutBinding.inflate(inflater, container, false)

        binding.floatingActionButton.setOnClickListener {
            viewModel.photoIndex = 1
            findNavController().navigate(R.id.action_allWorkoutsFragment_to_addWorkoutFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.items?.observe(viewLifecycleOwner){
            binding.recycler.adapter = ItemAdapter(it, object : ItemAdapter.ItemListener {
                override fun onItemClicked(index: Int) {
                    val item = (binding.recycler.adapter as ItemAdapter).itemAt(index)
                    viewModel.setItem(item)

                    findNavController().navigate(R.id.action_allWorkoutsFragment_to_itemDescription)
                }
                override fun onItemLongClicked(index: Int) {
                    val item = (binding.recycler.adapter as ItemAdapter).itemAt(index)
                    viewModel.deleteItem(item)
                }
            })
            binding.recycler.layoutManager = LinearLayoutManager(requireContext())

        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
