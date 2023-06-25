package com.example.workoutapp.data.local_db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.workoutapp.data.model.Exercise_Item
import com.example.workoutapp.data.model.Workout_Item
import dagger.hilt.android.AndroidEntryPoint


@Database(entities = [Workout_Item::class, Exercise_Item::class], version = 2)
@TypeConverters(Converters::class)
abstract class WorkoutItemDatabase : RoomDatabase() {

    abstract fun itemsDao() : ItemDao
    abstract fun exerciseDao() : ExerciseDao

    companion object {

        @Volatile
        private var instance: WorkoutItemDatabase? = null

        fun getDatabase(context: Context) = instance ?: synchronized(this) {
            Room.databaseBuilder(context.applicationContext, WorkoutItemDatabase::class.java, "db")
                    .build()
        }
    }
}