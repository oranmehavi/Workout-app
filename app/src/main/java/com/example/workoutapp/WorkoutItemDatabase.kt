package com.example.workoutapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Workout_Item::class), version = 1, exportSchema = false)
abstract class WorkoutItemDatabase : RoomDatabase() {

    abstract fun itemsDao() : ItemDao

    companion object {

        @Volatile
        private var instance: WorkoutItemDatabase? = null

        fun getDatabase(context: Context) = instance ?: synchronized(this) {
            Room.databaseBuilder(context.applicationContext, WorkoutItemDatabase::class.java, "db").
                    allowMainThreadQueries().build()
        }
    }
}