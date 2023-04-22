package com.example.workoutapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapp.databinding.AllWorkoutLayoutBinding

class AllWorkoutsFragment : Fragment() {

    private var _binding : AllWorkoutLayoutBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AllWorkoutLayoutBinding.inflate(inflater, container, false)

        binding.floatingActionButton.setOnClickListener {

            findNavController().navigate(R.id.action_allWorkoutsFragment_to_addWorkoutFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recycler.adapter = ItemAdapter(WorkoutManager.workouts, object : ItemAdapter.ItemListener {
            override fun onItemClicked(index: Int) {
                Toast.makeText(requireContext(), "${WorkoutManager.workouts[index].title}", Toast.LENGTH_SHORT).show()
            }
            override fun onItemLongClicked(index: Int) {
                WorkoutManager.remove(index)
                binding.recycler.adapter!!.notifyItemRemoved(index)
            }
        })
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())

        ItemTouchHelper(object : ItemTouchHelper.Callback(){
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ) = makeMovementFlags(
                ItemTouchHelper.ACTION_STATE_SWIPE and ItemTouchHelper.ACTION_STATE_DRAG,
                ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT or ItemTouchHelper.UP or ItemTouchHelper.DOWN)//makeFlag(ItemTouchHelper.ACTION_STATE_SWIPE, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT)

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                WorkoutManager.remove(viewHolder.adapterPosition)
                binding.recycler.adapter!!.notifyItemRemoved(viewHolder.adapterPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                WorkoutManager.remove(viewHolder.adapterPosition)
                binding.recycler.adapter!!.notifyItemRemoved(viewHolder.adapterPosition)
            }
        }).attachToRecyclerView(binding.recycler)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
