package com.candroid.realtracker.workout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.candroid.realtracker.R
import com.candroid.realtracker.databinding.ActivityCovidBinding
import com.candroid.realtracker.databinding.ActivityWorkoutBinding

class WorkoutActivity : AppCompatActivity() {
 lateinit var binding: ActivityWorkoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    binding=DataBindingUtil.setContentView(this,R.layout.activity_workout)
        binding.llstart.setOnClickListener {
            val intent = Intent(this,ExerciseActivity::class.java)
            startActivity(intent)
        }

    }
    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_l, R.anim.slide_out_r)
    }

}