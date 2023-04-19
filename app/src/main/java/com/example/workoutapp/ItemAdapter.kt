package com.example.workoutapp

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.workoutapp.databinding.WorkoutLayoutBinding

class ItemAdapter(val items: List<Workout_Item>, val callBack: ItemListener) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>(){

    interface ItemListener{
        fun onItemClicked(index : Int)
        fun onItemLongClicked(index : Int)
    }

    inner class ItemViewHolder(private val binding : WorkoutLayoutBinding)
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

        fun bind(item : Workout_Item){
            binding.workoutTitle.text = item.title
            //binding.itemDescription.text = item.description
            //TODO: Glide.with(binding.root).load(item.photo).circleCrop().into(binding.itemImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemViewHolder(
        WorkoutLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) =
        holder.bind(items[position])

    override fun getItemCount() = items.size
}