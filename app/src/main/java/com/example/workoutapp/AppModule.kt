package com.example.workoutapp

import android.content.Context
import com.example.workoutapp.api.WeatherAPI
import com.example.workoutapp.data.local_db.ItemDao
import com.example.workoutapp.data.local_db.WorkoutItemDatabase
import com.example.workoutapp.data.repository.WorkoutItemRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.annotation.Signed
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)

class AppModule {

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder().baseUrl("https://api.weatherapi.com/v1/").addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    @Singleton
    fun provideAPI(retrofit: Retrofit) : WeatherAPI {
        return retrofit.create(WeatherAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideLocalDatabase(@ApplicationContext appContext: Context) : WorkoutItemDatabase {
        return WorkoutItemDatabase.getDatabase(appContext)
    }

    @Provides
    @Singleton
    fun provideItemDao(database: WorkoutItemDatabase) = database.itemsDao()

    @Provides
    @Singleton
    fun provideExerciseDao(database: WorkoutItemDatabase) = database.exerciseDao()

}