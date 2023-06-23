package com.example.workoutapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@Entity(tableName = "Items")
data class Workout_Item @Inject constructor(
    @ColumnInfo(name="Title")
    val title: String,
    @ColumnInfo(name="Photo")
    val photo: String? ,
    @ColumnInfo(name="Description")
    val description: String,
    @ColumnInfo(name="Repeats")
    val repeats: String?,
    @ColumnInfo(name="Weight")
    val weight:String?,
    @ColumnInfo(name="DateAndTime")
    val dateAndTime: String) {

    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}