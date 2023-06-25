package com.example.workoutapp.ui.single_character

import android.app.Activity
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.workoutapp.R
import com.example.workoutapp.data.model.Exercise_Item

class ExerciseListAdapter (private val context: Activity, private val exercises: Array<Exercise_Item>?)
    : ArrayAdapter<Exercise_Item>(context, R.layout.exercise_list, exercises!!) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.exercise_list, null, true)

        val exercise_title = rowView.findViewById(R.id.exercise_name) as TextView
        val exercise_repeats = rowView.findViewById(R.id.repeats) as TextView
        val exercise_weight = rowView.findViewById(R.id.exercise_weight) as TextView
        val exercise_sets = rowView.findViewById(R.id.exercise_sets) as TextView

        exercise_title.text = exercises!![position].exerciseName
        exercise_repeats.text = exercises!![position].Reps
        exercise_weight.text = exercises!![position].Weight
        exercise_sets.text = exercises!![position].Sets


        return rowView
    }
}