package com.example.workoutapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addItem(workout_item: Workout_Item)

    @Delete
    fun deleteItem(vararg workout_items: Workout_Item)

    @Update
    fun updateItem(workout_item: Workout_Item)

    @Query("SELECT * FROM items ORDER BY workout_name ASC")
    fun getItems() : LiveData<List<Workout_Item>>

    @Query("SELECT * FROM items WHERE id LIKE :id")
    fun getItem(id: Int) : Workout_Item
}