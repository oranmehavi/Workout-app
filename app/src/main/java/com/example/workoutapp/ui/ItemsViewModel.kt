package com.example.workoutapp.ui

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.workoutapp.R
import com.example.workoutapp.data.repository.WorkoutItemRepository
import com.example.workoutapp.data.model.Workout_Item
import kotlinx.coroutines.launch


class ItemsViewModel(application: Application):AndroidViewModel(application) {

    private val repository = WorkoutItemRepository(application)
    val items: LiveData<List<Workout_Item>>? get() = repository.getItems()

    private val _chosenItem  = MutableLiveData<Workout_Item>()
    val chosenItem : LiveData<Workout_Item> get() = _chosenItem

    fun setItem(item: Workout_Item){
        _chosenItem.value = item
    }

    fun addItem(item: Workout_Item){
        viewModelScope.launch {
            repository.addItem(item)
        }
    }

    fun deleteItem(item: Workout_Item){
        viewModelScope.launch {
            repository.deleteItem(item)
        }
    }

    fun deleteAll(){
        repository.deleteAll()
    }

    private val _imageList = mutableListOf(
        //Order Matters here, if changed
        R.drawable.exercise1,
        R.drawable.exercise2,
        R.drawable.exercise3,
        R.drawable.exercise4,
        R.drawable.exercise5,
        R.drawable.exercise6,
        R.drawable.exercise7,
        R.drawable.exercise8
    )

    val imageList : MutableList<Int> get() = _imageList
    var photoIndex : Int = 1
    var photoURI: Uri? = null


}