package com.candroid.realtracker.habittracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.candroid.realtracker.R

class HabitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habit)


    }
    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_l, R.anim.slide_out_r)
    }

}