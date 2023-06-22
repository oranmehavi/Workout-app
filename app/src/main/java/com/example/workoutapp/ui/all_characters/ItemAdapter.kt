package com.example.workoutapp.ui.all_characters

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.workoutapp.data.model.Workout_Item
import com.example.workoutapp.databinding.WorkoutLayoutBinding

class ItemAdapter(private val items: List<Workout_Item>, val callBack: ItemListener) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>(){

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
            val NumericPhoto : Int? = item.photo?.toIntOrNull()
            binding.workoutTitle.text = item.title
            binding.dateAndTime.text = item.dateAndTime
            //Need to handle both URI  and Drawables so I need 2 use cases, one int one string
            if(NumericPhoto == null) {
                Glide.with(binding.root).load(item.photo).circleCrop().into(binding.workoutIcon)
            }
            else{
                Glide.with(binding.root).load(NumericPhoto).circleCrop().into(binding.workoutIcon)
            }
        }
    }

    fun itemAt(position:Int) = items[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemViewHolder(
        WorkoutLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) =
        holder.bind(items[position])

    override fun getItemCount() = items.size
}