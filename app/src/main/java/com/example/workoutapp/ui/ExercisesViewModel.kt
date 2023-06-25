package com.example.workoutapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.workoutapp.data.model.Exercise_Item
import com.example.workoutapp.data.repository.ExerciseItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExercisesViewModel @Inject constructor(application: Application):AndroidViewModel(application) {

    @Inject lateinit var repository: ExerciseItemRepository


    private val _chosenItem  = MutableLiveData<Exercise_Item>()
    val chosenItem : LiveData<Exercise_Item> get() = _chosenItem

    var workoutId: String = ""

    val items: LiveData<List<Exercise_Item>>? get() = repository.getExercises(workoutId)

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




}