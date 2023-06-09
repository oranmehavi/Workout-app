package com.example.workoutapp.ui

import android.app.Application
import android.net.Uri
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.workoutapp.R
import com.example.workoutapp.api.API_KEY
import com.example.workoutapp.api.WeatherAPI
import com.example.workoutapp.data.model.Exercise_Item
import com.example.workoutapp.data.model.WorkoutWithExercises
import com.example.workoutapp.data.repository.WorkoutItemRepository
import com.example.workoutapp.data.model.Workout_Item
import com.example.workoutapp.data.repository.ExerciseItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


const val TAG = "ItemsViewModel"

@HiltViewModel
class ItemsViewModel @Inject constructor(application: Application):AndroidViewModel(application) {


    @Inject lateinit var repository: WorkoutItemRepository


    val items: LiveData<List<Workout_Item>>? get() = repository.getItems()

    private val _chosenItem  = MutableLiveData<Workout_Item>()
    val chosenItem : LiveData<Workout_Item> get() = _chosenItem

    @Inject lateinit var api: WeatherAPI
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
        viewModelScope.launch {
            repository.deleteAll()
        }

    }

    fun getWorkoutWithSets(workoutId: String): LiveData<WorkoutWithExercises>? {

        return repository.getWorkoutWithSets(workoutId)

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
    var tempExerciseList = mutableListOf<Exercise_Item>()

    val location: LiveData<String> = LocationUpdatesLiveData(application.applicationContext)
    var temperature: Double? = null
    var weatherIcon: String? = null
    fun getWeather(coordinates: String) {

        viewModelScope.launch {
            try {
               val response = api.getWeather(
                   API_KEY,
                   coordinates)

                if (response.isSuccessful && response.body() != null) {
                    temperature = response.body()!!.current.temp_c
                    weatherIcon = response.body()!!.current.condition.icon
                }
            }
            catch (e: IOException) {
                Log.d(TAG, "IOException, You don't have an internet connection")
            } catch (e: HttpException) {
                Log.d(TAG, "HttpException, unexpected response")
                return@launch
            }


        }
    }

}