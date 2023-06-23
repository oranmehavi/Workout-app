package com.example.workoutapp.ui.add_character

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.workoutapp.R
import com.example.workoutapp.data.model.Workout_Item
import com.example.workoutapp.databinding.AddWorkoutLayoutBinding
import com.example.workoutapp.ui.ItemsViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Date

@AndroidEntryPoint
class AddWorkoutFragment : Fragment() {

    private var _binding : AddWorkoutLayoutBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ItemsViewModel by activityViewModels()

    private val locationRequestLauncher: ActivityResultLauncher<String>
        = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) {
                getLocationUpdates()
            }

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {
            getLocationUpdates()
        }
        else {
            locationRequestLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
        _binding = AddWorkoutLayoutBinding.inflate(inflater, container, false)

        //var photoURI: Uri? = null
        if (viewModel.photoIndex == 9) {
            Glide.with(this).load(viewModel.photoURI).into(binding.resultImage)
        } else {
            binding.resultImage.setImageResource(viewModel.imageList[viewModel.photoIndex - 1])
        }

        binding.finishBtn.setOnClickListener {
            val calendarInstance = Calendar.getInstance()
            val currentDateAndTime = "${calendarInstance.get(Calendar.DAY_OF_MONTH)}." +
                    "${calendarInstance.get(Calendar.MONTH) + 1}.${calendarInstance.get(Calendar.YEAR)}" +
                    "    ${calendarInstance.get(Calendar.HOUR_OF_DAY)}:${calendarInstance.get(Calendar.MINUTE)}"
            if (binding.workoutTitle.text?.isEmpty() != true && binding.workoutDesc.text?.isEmpty() != true) {
                val item = Workout_Item(
                    binding.workoutTitle.text.toString(),
                    if (viewModel.photoIndex != 9) viewModel.imageList[viewModel.photoIndex - 1].toString() else viewModel.photoURI?.toString(),
                    binding.workoutDesc.text.toString(),
                    binding.workoutRepeats.text.toString(),
                    binding.workoutWeight.text.toString(),
                    currentDateAndTime
                )
                viewModel.addItem(item)
                findNavController().navigate(R.id.action_addWorkoutFragment_to_allWorkoutsFragment)
            }
            else{
                Toast.makeText(requireContext(), getString(R.string.empty_workout_warning), Toast.LENGTH_SHORT).show()
            }
        }

        binding.imageBtn.setOnClickListener {
            findNavController().navigate(R.id.action_addWorkoutFragment_to_pictureSelectFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getLocationUpdates() {

        viewModel.location.observe(viewLifecycleOwner) {
            viewModel.getWeather(it)
            if (viewModel.temperature != null && viewModel.weatherIcon != null) {
                val temperature = viewModel.temperature?.toInt()
                binding.temperature.text = temperature.toString() + "°C"
                if (temperature!! >= 35 || temperature!! < 20) binding.weatherText.text = getString(R.string.critical_weather)
                if (temperature!! >= 20 || temperature!! <= 30) binding.weatherText.text = getString(R.string.comfortable_weather)
                if (temperature!! > 30 || temperature!! < 35) binding.weatherText.text = getString(R.string.drink_water)
                binding.weatherText.visibility = View.VISIBLE
                binding.temperature.visibility = View.VISIBLE
                Glide.with(this).load("https://" + viewModel.weatherIcon!!).into(binding.weatherIcon)
            }

        }
    }
}

