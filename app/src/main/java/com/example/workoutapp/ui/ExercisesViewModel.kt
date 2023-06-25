package com.example.workoutapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.workoutapp.data.model.Exercise_Item
import com.example.workoutapp.data.repository.ExerciseItemRepository
import kotlinx.coroutines.launch


class ExercisesViewModel(application: Application):AndroidViewModel(application) {

    private val repository = ExerciseItemRepository(application)
    val items: LiveData<List<Exercise_Item>>? get() = repository.getExercises()

    private val _chosenItem  = MutableLiveData<Exercise_Item>()
    val chosenItem : LiveData<Exercise_Item> get() = _chosenItem

    fun setItem(item: Exercise_Item){
        _chosenItem.value = item
    }

    fun addItem(item: Exercise_Item){
        viewModelScope.launch {
            repository.addExercise(item)
        }
    }

    fun deleteItem(item: Exercise_Item){
        viewModelScope.launch {
            repository.deleteExercise(item)
        }
    }

    fun deleteAll(){
        repository.deleteAll()
    }


}