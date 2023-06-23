package com.example.workoutapp

import android.provider.SyncStateContract.Constants
import com.example.workoutapp.api.WeatherAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)

class AppModule {

    @Provides
    @Singleton

    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder().baseUrl("https://api.weatherapi.com/v1/").addConverterFactory(GsonConverterFactory.create()).build()
    }

}