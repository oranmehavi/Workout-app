package com.example.workoutapp.data.local_db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.workoutapp.data.model.Workout_Item
import dagger.hilt.android.AndroidEntryPoint


@Database(entities = [Workout_Item::class], version = 2)
abstract class WorkoutItemDatabase : RoomDatabase() {

    abstract fun itemsDao() : ItemDao

    companion object {

        @Volatile
        private var instance: WorkoutItemDatabase? = null

        fun getDatabase(context: Context) = instance ?: synchronized(this) {
            Room.databaseBuilder(context.applicationContext, WorkoutItemDatabase::class.java, "db")
                    .build()
        }
    }
}