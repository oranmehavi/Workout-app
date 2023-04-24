package com.example.workoutapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.workoutapp.R
import com.example.workoutapp.data.repository.WorkoutItemRepository
import com.example.workoutapp.data.model.Workout_Item


class ItemsViewModel(application: Application):AndroidViewModel(application) {

    private val repository = WorkoutItemRepository(application)
    val items: LiveData<List<Workout_Item>>? get() = repository.getItems()

    private val _chosenItem  = MutableLiveData<Workout_Item>()
    val chosenItem : LiveData<Workout_Item> get() = _chosenItem

    fun setItem(item: Workout_Item){
        _chosenItem.value = item
    }

    fun addItem(item: Workout_Item){
        repository.addItem(item)
    }

    fun deleteItem(item: Workout_Item){
        repository.deleteItem(item)
    }

    private val _imageList = mutableListOf(
        R.drawable.exercise1,
        R.drawable.exercise2,
        R.drawable.exercise3,
        R.drawable.exercise4
    )

    val imageList: MutableList<Int> get() = _imageList

    private var index = 0

    fun getIndex(): Int {
        return index
    }

    fun plusOneIndex(){
        index = (index + 1) % _imageList.size

    }


}