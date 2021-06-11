package com.candroid.realtracker.optionfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.candroid.realtracker.R

class PolActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pol)
        supportFragmentManager.beginTransaction()
            .replace(R.id.policy,PolicyFragment())
            .commit()
        supportActionBar?.title="Policy"


    }
    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_l, R.anim.slide_out_r)
    }

}