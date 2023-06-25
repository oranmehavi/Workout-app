package com.example.workoutapp.data.repository

import android.app.Application
import android.content.ClipData.Item
import com.example.workoutapp.data.local_db.ItemDao
import com.example.workoutapp.data.local_db.WorkoutItemDatabase
import com.example.workoutapp.data.model.Workout_Item
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Singleton
class WorkoutItemRepository @Inject constructor(private val itemDao: ItemDao)  {



    fun getItems() = itemDao.getItems()

    suspend fun addItem(workout_item: Workout_Item) {
            itemDao.addItem(workout_item)
    }

    suspend fun deleteItem(workout_item: Workout_Item) {
            itemDao.deleteItem(workout_item)
    }

    fun getItem(id: Int) = itemDao.getItem(id)
    suspend fun deleteAll() = itemDao?.deleteAll()

    fun getWorkoutWithSets(workoutId: String) = itemDao?.getWorkoutWithSets(workoutId)

}