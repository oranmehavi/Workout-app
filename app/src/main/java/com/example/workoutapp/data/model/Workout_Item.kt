package com.example.workoutapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Items")
data class Workout_Item(
    @ColumnInfo(name="Title")
    val title: String,
    @ColumnInfo(name="Photo")
    val photo: Int,
    @ColumnInfo(name="Description")
    val description: String,
    @ColumnInfo(name="Repeats")
    val repeats: String?,
    @ColumnInfo(name="Weight")
    val weight:String?) {

    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}