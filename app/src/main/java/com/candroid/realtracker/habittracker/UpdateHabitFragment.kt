package com.candroid.realtracker.habittracker

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.candroid.realtracker.R
import com.candroid.realtracker.habittracker.habit_database.HabitDatabase
import com.candroid.realtracker.habittracker.habitrepo.HabitRepository
import com.candroid.realtracker.habittracker.habitrepo.ViewModelFactory
import com.candroid.realtracker.habittracker.ui.HabitViewModel
import kotlinx.android.synthetic.main.fragment_update_habit.*
import java.util.*


class UpdateHabitFragment : Fragment(R.layout.fragment_update_habit) , TimePickerDialog.OnTimeSetListener,
    DatePickerDialog.OnDateSetListener {
    var titles = ""
    var descriptions = ""
    var timeStamps = ""
    var drawableSelecteds = 0
    var day = 0
    var month = 0
    var year = 0
    var minutes = 0
    var hour = 0
    var cleanTime = ""
    var cleanDate = ""
    lateinit var habitViewModel: HabitViewModel
    private val args by navArgs<UpdateHabitFragmentArgs>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val db = HabitDatabase(activity as Context)
        val habitRepository = HabitRepository(db)
        val factory = ViewModelFactory(habitRepository)
        habitViewModel = ViewModelProvider(this, factory).get(HabitViewModel::class.java)
        etHabitTitleUpdate.editText?.setText(args.selectedHabit.habit_title)
        etHabitDescriptionUpdate.editText?.setText(args.selectedHabit.habit_description)
        drawableSelected()
        pickTime()
        setHasOptionsMenu(true)
        btnConfirmUpdate.setOnClickListener {
            titles=etHabitTitleUpdate.editText?.text.toString().trim()
            descriptions=etHabitDescriptionUpdate.editText?.text.toString().trim()
            timeStamps="$cleanDate $cleanTime"
            if( !(titles.isEmpty()||descriptions.isEmpty()||timeStamps.isEmpty()||drawableSelecteds==0)) {
                var habit= Habit(1,titles,descriptions,timeStamps,drawableSelecteds)
                habitViewModel.updateHabit(habit)
                Toast.makeText(context, "Successfully update your habit!", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_updateHabitFragment_to_habitHomeFragment)
            }
            else {
                Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            }
        }
        btnDelete.setOnClickListener {
    deleteUpdate(args.selectedHabit)



        }

    }


    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        cleanTime = Calculations.cleanTime(hourOfDay, minute)
        tvTimeSelectedUpdate.text = "TIME:$cleanTime"

    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        cleanDate = Calculations.cleanDate(year, month, dayOfMonth)
        tvDateSelectedUpdate.text = "DATE:$cleanDate"

    }

    private fun drawableSelected() {


        ivFastFoodSelectedUpdate.setOnClickListener {
            ivFastFoodSelectedUpdate.isSelected = !ivFastFoodSelectedUpdate.isSelected
            drawableSelecteds = R.drawable.fast_food_s

            //de-select the other options when we pick an image
            ivSmokingSelectedUpdate.isSelected = false
            ivTeaSelectedUpdate.isSelected = false

        }

        ivSmokingSelectedUpdate.setOnClickListener {
            ivSmokingSelectedUpdate.isSelected = !ivSmokingSelectedUpdate.isSelected
            drawableSelecteds = R.drawable.hot_cup

            //de-select the other options when we pick an image
            ivFastFoodSelectedUpdate.isSelected = false
            ivTeaSelectedUpdate.isSelected = false
        }

        ivTeaSelectedUpdate.setOnClickListener {
            ivTeaSelectedUpdate.isSelected = !ivTeaSelectedUpdate.isSelected
            drawableSelecteds = R.drawable.no_smoking_k

            //de-select the other options when we pick an image
            ivFastFoodSelectedUpdate.isSelected = false
            ivSmokingSelectedUpdate.isSelected = false
        }

    }

    fun pickTime() {
        btnPickDateUpdate.setOnClickListener {
            getDate()
            DatePickerDialog(requireContext(), this, year, month, day).show()
        }
        btnPickTimeUpdate.setOnClickListener {
            getTime()
            TimePickerDialog(context, this, hour, minutes, true).show()
        }

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
        minutes = cal.get(Calendar.MINUTE)



    }


    private fun deleteUpdate(habit:Habit)
    {

        val alertDailog= AlertDialog.Builder(context)
            .setTitle("DELETE")
            .setMessage("ARE YOU SURE!!")
            .setPositiveButton("Ok"){_,_->
                habitViewModel.deleteHabit(habit)
                Toast.makeText(context, "Successfully delete your habit!", Toast.LENGTH_LONG).show()

                findNavController().navigate(R.id.action_updateHabitFragment_to_habitHomeFragment)
            }
            .setNegativeButton("CANCEL"){_,_->

            }.create().show()





    }


}
