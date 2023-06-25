package com.example.workoutapp.data.repository

import android.app.Application
import com.example.workoutapp.data.local_db.ExerciseDao
import com.example.workoutapp.data.local_db.ItemDao
import com.example.workoutapp.data.local_db.WorkoutItemDatabase
import com.example.workoutapp.data.model.Exercise_Item
import com.example.workoutapp.data.model.WorkoutWithExercises
import com.example.workoutapp.data.model.Workout_Item
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ExerciseItemRepository(application: Application)  {


    private var exerciseDao : ExerciseDao?

    init {
        val db = WorkoutItemDatabase.getDatabase(application.applicationContext)
        exerciseDao = db?.exerciseDao()
    }

    fun getExercises() = exerciseDao?.getExercises()

    suspend fun addExercise(exercise_item: Exercise_Item) {
        exerciseDao?.addExercise(exercise_item)
    }

    suspend fun deleteExercise(exercise_item: Exercise_Item) {
        exerciseDao?.deleteExercise(exercise_item)
    }

    fun getItem(id: Int) = exerciseDao?.getExercise(id)

    fun deleteAll() = exerciseDao?.deleteAll()


}