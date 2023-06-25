package com.example.workoutapp.ui.all_characters

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapp.data.model.Exercise_Item
import com.example.workoutapp.databinding.ExerciseLayoutBinding

class ExerciseAdapter(private val items: List<Exercise_Item>, val callBack: ItemListener) : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>(){

    interface ItemListener{
        fun onItemClicked(index : Int)
        fun onItemLongClicked(index : Int)
    }

    inner class ExerciseViewHolder(private val binding : ExerciseLayoutBinding)
        : RecyclerView.ViewHolder(binding.root), OnClickListener, OnLongClickListener{

        init {
            binding.root.setOnClickListener(this)
            binding.root.setOnLongClickListener(this)
        }
        override fun onClick(p0: View?) {
            callBack.onItemClicked(adapterPosition)
        }

        override fun onLongClick(p0: View?): Boolean {
            callBack.onItemLongClicked(adapterPosition)
            return true
        }

        fun bind(item : Exercise_Item){
            binding.exerciseName.text = item.exerciseName
            binding.exerciseSets.text = item.Sets
            binding.exerciseReps.text = item.Reps
            binding.exerciseWeight.text = item.Weight
        }
    }

    fun itemAt(position:Int) = items[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ExerciseViewHolder(
        ExerciseLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: ExerciseAdapter.ExerciseViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount() = items.size
}