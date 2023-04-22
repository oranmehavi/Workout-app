package com.example.workoutapp

import android.graphics.drawable.Drawable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class Workout_Item(
    @ColumnInfo(name = "workout_name")
    val title: String,
    @ColumnInfo(name = "workout_desc")
    val description: String,
    @ColumnInfo(name = "image")
    val photo: Drawable?) {

    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}

object WorkoutManager{
    val workouts : MutableList<Workout_Item> = mutableListOf()

    fun add(workout: Workout_Item){
        workouts.add(workout)
    }

    fun remove(index : Int){
        workouts.removeAt(index)
    }
}