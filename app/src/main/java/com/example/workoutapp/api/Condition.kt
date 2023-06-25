package com.example.workoutapp.api

import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


data class Condition @Inject constructor(
    val code: Int,
    val icon: String,
    val text: String
)