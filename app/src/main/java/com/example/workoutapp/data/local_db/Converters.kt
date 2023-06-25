package com.example.workoutapp.data.local_db

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.workoutapp.data.model.Exercise_Item
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converters {
    @TypeConverter
    fun fromExerciseItemList(exerciseItemList: List<Exercise_Item>): String {
        return Gson().toJson(exerciseItemList)
    }

    @TypeConverter
    fun toExerciseItemList(json: String): List<Exercise_Item> {
        val listType = object : TypeToken<List<Exercise_Item>>() {}.type
        return Gson().fromJson(json, listType)
    }
}