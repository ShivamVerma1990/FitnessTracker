package com.candroid.realtracker.optionfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.candroid.realtracker.R

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        supportFragmentManager.beginTransaction()
            .replace(R.id.about,AboutFragment())
            .commit()
        supportActionBar?.title="About"

    }
    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_l, R.anim.slide_out_r)
    }

}