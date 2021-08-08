package com.candroid.realtracker.stepCounter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.candroid.realtracker.R

class StepActivity : AppCompatActivity(), stepsCallback {
    lateinit var TV_STEPS:TextView
    lateinit var TV_CALORIES:TextView
    lateinit var TV_DISTANCE:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step)
        TV_DISTANCE=findViewById(R.id.TV_DISTANCE)
        TV_CALORIES=findViewById(R.id.TV_CALORIES)
        TV_STEPS=findViewById(R.id.TV_STEPS)
        val intent = Intent(applicationContext, StepDetectorService::class.java)
        startService(intent)

        StepDetectorService.subscribe.register(this)
    }

    override fun subscribeSteps(steps: Int) {
        TV_STEPS.setText(steps.toString())
        TV_CALORIES.setText(GeneralHelper.getCalories(steps))
        TV_DISTANCE.setText(GeneralHelper.getDistanceCovered(steps))
    }
}