package com.candroid.realtracker.stepCounter

import android.content.Context
import android.content.SharedPreferences
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.candroid.realtracker.R
import com.mikhaellopez.circularprogressbar.CircularProgressBar

class StepActivity : AppCompatActivity(), SensorEventListener {
    lateinit var sharedPreferences: SharedPreferences
    lateinit var TV_DISTANCE: TextView
    lateinit var  TV_CALORIES: TextView
    lateinit var tv_stepsTaken: TextView
    lateinit var tv_totalMax: TextView
    private var previousStep=0f
    private var totalStep=0f
    lateinit var circularProgressBar: CircularProgressBar

    var isRunning=false
    var sensorManager: SensorManager?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step)
        sharedPreferences=getSharedPreferences("myPref", Context.MODE_PRIVATE)
        TV_CALORIES=findViewById(R.id.TV_CALORIES)
        TV_DISTANCE=findViewById(R.id.TV_DISTANCE)
        circularProgressBar=findViewById(R.id.circularProgressBar)
        tv_stepsTaken=findViewById(R.id.tv_stepsTaken)
        tv_totalMax=findViewById(R.id.tv_totalMax)
        load()
        reset()

        sensorManager=getSystemService(Context.SENSOR_SERVICE) as SensorManager

    }


    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onResume() {
        super.onResume()
        isRunning=true
        val countStep=sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

        if(countStep==null){


        }
        else{
            sensorManager?.registerListener(this,countStep, SensorManager.SENSOR_DELAY_UI)


        }

    }

    override fun onSensorChanged(p0: SensorEvent?) {
        if(isRunning) {

            totalStep = p0!!.values[0]
            val currentStep = totalStep.toInt() - previousStep.toInt()


            tv_stepsTaken.text = ("$currentStep")
            TV_DISTANCE.text = GeneralHelper.getDistanceCovered(currentStep)
            TV_CALORIES.text = GeneralHelper.getCalories(currentStep)

            GeneralHelper.updateNotification(this, currentStep)

            circularProgressBar.apply {
                setProgressWithAnimation(currentStep.toFloat())
            }
//
//}else if(currentStep==249){
//    tv_stepsTaken.text=("${0}")
//    TV_DISTANCE.text=GeneralHelper.getDistanceCovered(0)
//    TV_CALORIES.text=GeneralHelper.getCalories(0)
//
//    GeneralHelper.updateNotification(this,currentStep)
//
//    circularProgressBar.apply {
//        setProgressWithAnimation(0.0f)
//    }
//
//
//}else{
//
//    tv_stepsTaken.text=("${0}")
//    TV_DISTANCE.text=GeneralHelper.getDistanceCovered(0)
//    TV_CALORIES.text=GeneralHelper.getCalories(0)
//
//    GeneralHelper.updateNotification(this,currentStep)
//
//    circularProgressBar.apply {
//        setProgressWithAnimation(0.0f)
//    }

        }
}


        




    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

    fun reset(){

        tv_stepsTaken.setOnClickListener {
            Toast.makeText(this,"Press long to reset your all status!",Toast.LENGTH_SHORT).show()

        }
        tv_stepsTaken.setOnClickListener {

            previousStep = totalStep

            tv_stepsTaken.text="${0.0f}"
            TV_CALORIES.text="${0.0f}"
            TV_DISTANCE.text="${0.0f}"
circularProgressBar.setProgressWithAnimation(0.0f)
            saveData()

            return@setOnClickListener
        }
    }
    fun saveData(){
        val sharedPreferences=getSharedPreferences("myPref", Context.MODE_PRIVATE)
        sharedPreferences.edit().putFloat("key1",previousStep).apply()


    }

    fun load(){
        val saveNumber=sharedPreferences.getFloat("key1",0f)
        previousStep=saveNumber

    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_l, R.anim.slide_out_r)
    }

}


