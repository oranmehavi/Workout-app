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
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Singleton
class ExerciseItemRepository @Inject constructor(private val exerciseDao: ExerciseDao){



    fun getExercises(workoutId: String) = exerciseDao?.getExercises(workoutId)

    suspend fun addExercise(exercise_item: Exercise_Item) {
        exerciseDao?.addExercise(exercise_item)
    }

    suspend fun deleteExercise(exercise_item: Exercise_Item) {
        exerciseDao?.deleteExercise(exercise_item)
    }

    fun getItem(id: Int) = exerciseDao?.getExercise(id)


}