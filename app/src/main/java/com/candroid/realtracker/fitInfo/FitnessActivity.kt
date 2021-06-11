package com.candroid.realtracker.fitInfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.candroid.realtracker.R

class FitnessActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fitness)
    }
    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_l, R.anim.slide_out_r)
    }

}
