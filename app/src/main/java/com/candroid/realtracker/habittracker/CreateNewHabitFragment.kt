package com.candroid.realtracker.habittracker

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.candroid.realtracker.R
import com.candroid.realtracker.habittracker.habit_database.HabitDatabase
import com.candroid.realtracker.habittracker.habitrepo.HabitRepository
import com.candroid.realtracker.habittracker.habitrepo.ViewModelFactory
import com.candroid.realtracker.habittracker.ui.HabitViewModel
import com.candroid.realtracker.habittracker.util.Calculations
import com.shivam.habittrakker.alarmservices.AlarmServices
import kotlinx.android.synthetic.main.fragment_create_new_habit.*
import java.util.*


class CreateNewHabitFragment : Fragment(R.layout.fragment_create_new_habit),
    TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {
    lateinit var alarmServices: AlarmServices



    private var titles = ""
    private var description = ""
    private var drawableSelected = 0
  private var timeStampHabit = ""

    private lateinit var habitViewModel: HabitViewModel

    private var day = 0
    private var month = 0
    private var year = 0
    private var hour = 0
    private var minute = 0

    private var cleanDate = ""
    private var cleanTime = ""


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        alarmServices= context?.let { AlarmServices(it) }!!

        val db = HabitDatabase(activity as Context)
        val habitRepository = HabitRepository(db)
        val factory = ViewModelFactory(habitRepository)
        habitViewModel = ViewModelProvider(this, factory).get(HabitViewModel::class.java)


        //Add habit to database
        btn_confirm_create.setOnClickListener {
            addHabitToDB()
        }
        imageView.setOnClickListener {
            setAlarm { alarmServices.setRepetitiveAlarm(it) }


            imageView.visibility=View.GONE
            imageView2.visibility=View.VISIBLE
            Toast.makeText(context,"yup!",Toast.LENGTH_LONG).show()
        }

        //Pick a date and time


        pickDateAndTime()

        //Selected and image to put into our list
        drawableSelected()

//        btn_pickDateAndTime.setOnClickListener {
//            setAlarm { alarmServices.setRepetitiveAlarm(it) }
//        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    //set on click listeners for our data and time pickers
    private fun pickDateAndTime() {
        btnPickDate.setOnClickListener {
            getDateCalendar()
            DatePickerDialog(requireContext(), this, year, month, day).show()
        }

        btnPickTime.setOnClickListener {
            getTimeCalendar()
            TimePickerDialog(context, this, hour, minute, true).show()
        }
    }

    private fun addHabitToDB() {

        //Get text from editTexts
        titles = inputTitle.editText?.text.toString().trim()
        description = etHabitDescription.editText?.text.toString().trim()
        timeStampHabit = "$cleanDate $cleanTime"
        //Create a timestamp string for our recyclerview
        timeStampHabit = "$cleanDate $cleanTime"

        //Check that the form is complete before submitting data to the database
        if (!(titles.isEmpty() || description.isEmpty() || timeStampHabit.isEmpty() || drawableSelected == 0)) {
            val habit = Habit(0, titles, description, timeStampHabit, drawableSelected)

            //add the habit if all the fields are filled
            habitViewModel.insertHabit(habit)
            Toast.makeText(context, "Habit created successfully!", Toast.LENGTH_SHORT).show()

            //navigate back to our home fragment
            findNavController().navigate(R.id.action_createNewHabitFragment_to_habitHomeFragment)
        } else {
            Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_SHORT).show()
        }
    }

    // Create a selector for our icons which will appear in the recycler view
    private fun drawableSelected() {
        ivFastFoodSelected.setOnClickListener {
            ivFastFoodSelected.isSelected = !ivFastFoodSelected.isSelected
            drawableSelected = R.drawable.fast_food_s

            //de-select the other options when we pick an image
            ivSmokingSelected.isSelected = false
            ivTeaSelected.isSelected = false
        }

        ivSmokingSelected.setOnClickListener {
            ivSmokingSelected.isSelected = !ivSmokingSelected.isSelected
            drawableSelected = R.drawable.hot_cup

            //de-select the other options when we pick an image
            ivFastFoodSelected.isSelected = false
            ivTeaSelected.isSelected = false
        }

        ivTeaSelected.setOnClickListener {
            ivTeaSelected.isSelected = !ivTeaSelected.isSelected
            drawableSelected = R.drawable.no_smoking_k

            //de-select the other options when we pick an image
            ivFastFoodSelected.isSelected = false
            ivSmokingSelected.isSelected = false
        }


    }

    //get the time set
    override fun onTimeSet(TimePicker: TimePicker?, p1: Int, p2: Int) {
        Log.d("Fragment", "Time: $p1:$p2")

        cleanTime = Calculations.cleanTime(p1, p2)
        tvTimeSelected.text = "Time: $cleanTime"
    }

    //get the date set
    override fun onDateSet(p0: DatePicker?, yearX: Int, monthX: Int, dayX: Int) {

        cleanDate = Calculations.cleanDate(dayX, monthX, yearX)
        tvDateSelected.text = "Date: $cleanDate"
    }

    //get the current time
    private fun getTimeCalendar() {
        val cal = Calendar.getInstance()
        hour = cal.get(Calendar.HOUR_OF_DAY)
        minute = cal.get(Calendar.MINUTE)
    }

    //get the current date
    private fun getDateCalendar() {
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
    }


    @SuppressLint("SetTextI18n")
    fun setAlarm(callback:(Long)->Unit){
        Calendar.getInstance().apply {
            this.set(Calendar.SECOND, 0)
            this.set(Calendar.MILLISECOND, 0)
            context?.let {
                DatePickerDialog(
                    it,0,DatePickerDialog.OnDateSetListener { _, day, month, year->
                        this.set(Calendar.MONTH,month)
                        this.set(Calendar.YEAR,year)
                        this.set(Calendar.DAY_OF_MONTH,day)
                        TimePickerDialog(context,TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->

                            this.set(Calendar.MINUTE,minute)
                            this.set(Calendar.HOUR_OF_DAY,hourOfDay)
                            callback(this.timeInMillis)

                            textv.text="DATE:$day/$month/$year TIME:$hourOfDay::$minute "
                        },this.get(Calendar.HOUR_OF_DAY),
                            this.get(Calendar.MINUTE),false).show()


                    },
                    this.get(Calendar.DAY_OF_MONTH),
                    this.get(Calendar.WEEK_OF_YEAR),
                    this.get(Calendar.DAY_OF_YEAR)

                ).show()
            }



        }

    }

    }

