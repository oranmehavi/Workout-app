package com.example.workoutapp.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class WorkoutWithExercises(
    @Embedded
    val workout : Workout_Item,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val exercises : List<Exercise_Item>?
)