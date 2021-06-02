package com.candroid.realtracker.habittracker

import Calculations
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.candroid.realtracker.R
import com.candroid.realtracker.habittracker.habit_database.HabitDatabase
import com.candroid.realtracker.habittracker.habitrepo.HabitRepository
import com.candroid.realtracker.habittracker.habitrepo.ViewModelFactory
import com.candroid.realtracker.habittracker.ui.HabitViewModel
import kotlinx.android.synthetic.main.fragment_create_new_habit.*
import java.util.*


class CreateNewHabitFragment : Fragment(R.layout.fragment_create_new_habit),
    TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {


    lateinit var habitViewModel: HabitViewModel
    var titles = ""
    var description = ""
    var timeStampHabit = ""
    var drawerSelected = 0
    var day = 0
    var month = 0
    var year = 0
    var hour = 0
    var minute = 0
    var cleanTime = ""
    var cleanDate = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db = HabitDatabase(activity as Context)
        val habitRepository = HabitRepository(db)
        val factory = ViewModelFactory(habitRepository)
        habitViewModel = ViewModelProvider(this, factory).get(HabitViewModel::class.java)







        btn_confirm_create.setOnClickListener {
            habitDb()
        }

        drawerSelecteds()
        pickDateAndTime()

    }

    private fun pickDateAndTime() {
        btnPickDate.setOnClickListener {
            getDate()
            DatePickerDialog(requireContext(), this, year, month, day).show()
        }
        btnPickTime.setOnClickListener {
            getTime()
            TimePickerDialog(context, this, hour, minute, true).show()

        }

    }

    private fun drawerSelecteds() {

        ivFastFoodSelected.setOnClickListener {
            ivFastFoodSelected.isSelected = !ivFastFoodSelected.isSelected
            drawerSelected = R.drawable.fast_food_s

            //de-select the other options when we pick an image
            ivSmokingSelected.isSelected = false
            ivTeaSelected.isSelected = false

        }

        ivSmokingSelected.setOnClickListener {
            ivSmokingSelected.isSelected = !ivSmokingSelected.isSelected
            drawerSelected = R.drawable.hot_cup

            //de-select the other options when we pick an image
            ivFastFoodSelected.isSelected = false
            ivTeaSelected.isSelected = false
        }

        ivTeaSelected.setOnClickListener {
            ivTeaSelected.isSelected = !ivTeaSelected.isSelected
            drawerSelected = R.drawable.no_smoking_k

            //de-select the other options when we pick an image
            ivFastFoodSelected.isSelected = false
            ivSmokingSelected.isSelected = false
        }


    }


    private fun habitDb() {
        titles = inputTitle.editText?.text.toString().trim()
        description = etHabitDescription.editText?.text.toString().trim()
        timeStampHabit = "$cleanDate $cleanTime"
        if (!(titles.isEmpty() || description.isEmpty() || timeStampHabit.isEmpty() || drawerSelected == 0)) {
            val habit = Habit(0,titles, description, timeStampHabit, drawerSelected)
            habitViewModel.insertHabit(habit)
            Toast.makeText(context, "Successfully add your habit!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_createNewHabitFragment_to_habitHomeFragment)
        } else {

            Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_SHORT).show()

        }


    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        cleanTime = Calculations.cleanTime(hourOfDay, minute)
        tvTimeSelected.text = "TIME:$cleanTime"
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        cleanDate = Calculations.cleanDate(dayOfMonth, month, year)
        tvDateSelected.text = "DATE:$cleanDate"

    }

    fun getDate() {
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
    }

    fun getTime() {
        val cal = Calendar.getInstance()
        hour = cal.get(Calendar.HOUR_OF_DAY)
        minute = cal.get(Calendar.MINUTE)
    }

}

