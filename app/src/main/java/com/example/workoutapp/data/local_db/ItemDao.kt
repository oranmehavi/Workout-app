package com.example.workoutapp.data.local_db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.workoutapp.data.model.Exercise_Item
import com.example.workoutapp.data.model.WorkoutWithExercises
import com.example.workoutapp.data.model.Workout_Item
import dagger.hilt.android.AndroidEntryPoint


@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItem(item: Workout_Item)

    @Delete
    suspend fun deleteItem(vararg item: Workout_Item)

    @Query("SELECT * FROM Items")
    fun getItems() : LiveData<List<Workout_Item>>

    @Query("SELECT * FROM items WHERE ID lIKE :id")
    fun getItem(id:Int): Workout_Item

    @Query("DELETE FROM items")
    suspend fun deleteAll()

    @Query("SELECT * FROM items WHERE workoutId = :id")
    fun getWorkoutWithSets(id: String) : LiveData<WorkoutWithExercises>

}