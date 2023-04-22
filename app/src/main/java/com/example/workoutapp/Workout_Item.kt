package com.example.workoutapp

data class Workout_Item(val title: String, val photo: String?)

object WorkoutManager{
    val workouts : MutableList<Workout_Item> = mutableListOf()

    fun add(workout: Workout_Item){
        workouts.add(workout)
    }

    fun remove(index : Int){
        workouts.removeAt(index)
    }
}