package com.example.workoutapp.api

import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


data class Weather @Inject constructor(
    val current: Current,
    val location: Location
)