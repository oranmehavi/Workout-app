package com.example.workoutapp.data.repository

import android.app.Application
import com.example.workoutapp.data.local_db.ItemDao
import com.example.workoutapp.data.local_db.WorkoutItemDatabase
import com.example.workoutapp.data.model.Workout_Item
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


class WorkoutItemRepository @Inject constructor(application: Application)  {


    private var itemDao : ItemDao?

    init {
        val db = WorkoutItemDatabase.getDatabase(application.applicationContext)
        itemDao = db.itemsDao()
    }

    fun getItems() = itemDao?.getItems()

    suspend fun addItem(workout_item: Workout_Item) {
            itemDao?.addItem(workout_item)
    }

    suspend fun deleteItem(workout_item: Workout_Item) {
            itemDao?.deleteItem(workout_item)
    }

    fun getItem(id: Int) = itemDao?.getItem(id)

}