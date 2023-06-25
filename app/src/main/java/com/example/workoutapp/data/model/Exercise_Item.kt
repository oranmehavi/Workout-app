package com.example.workoutapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Exercises")
data class Exercise_Item(
    @ColumnInfo(name="Exercise Name")
    val exerciseName : String,
    @ColumnInfo(name="Weight")
    val Weight : String,
    @ColumnInfo(name="Reps")
    val Reps: String,
    @ColumnInfo(name="Sets")
    val Sets : String
){
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
    @ColumnInfo(name = "Exercise List")
    var exerciseList = mutableListOf<Exercise_Item>()
}
