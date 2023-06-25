package com.example.workoutapp.data.local_db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.workoutapp.data.model.Exercise_Item

@Dao
interface ExerciseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addExercise(item: Exercise_Item)

    @Delete
    suspend fun deleteExercise(vararg item: Exercise_Item)

    @Query("SELECT * FROM Exercises")
    fun getExercises() : LiveData<List<Exercise_Item>>

    @Query("SELECT * FROM Exercises WHERE ID lIKE :id")
    fun getExercise(id:Int): Exercise_Item

    @Query("DELETE FROM Exercises")
    fun deleteAll()

}
