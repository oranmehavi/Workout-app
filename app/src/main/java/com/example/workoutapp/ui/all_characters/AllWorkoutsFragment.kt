package com.example.workoutapp.ui.all_characters

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workoutapp.R
import com.example.workoutapp.databinding.AllWorkoutLayoutBinding
import com.example.workoutapp.ui.ItemsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AllWorkoutsFragment @Inject constructor(): Fragment() {

    private var _binding : AllWorkoutLayoutBinding? = null

    private val binding get() = _binding!!

    private val viewModel: ItemsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        setHasOptionsMenu(true)
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_delete){
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle(getString(R.string.confirm_delete))
                .setMessage(getString(R.string.delete_confirmation_message))
                .setPositiveButton(getString(R.string.yes))
                { p0, p1 ->
                    viewModel.deleteAll()
                    Toast.makeText(requireContext(), getString(R.string.all_items_delete_confirmed) , Toast.LENGTH_SHORT).show()}
                .show()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
