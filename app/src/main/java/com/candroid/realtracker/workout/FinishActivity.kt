package com.candroid.realtracker.workout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.candroid.realtracker.R
import com.candroid.realtracker.databinding.ActivityExerciseBinding
import com.candroid.realtracker.databinding.ActivityFinishBinding
import kotlinx.android.synthetic.main.activity_finish.*

class FinishActivity : AppCompatActivity() {
lateinit var toolbar_finish_activity:Toolbar
 lateinit var btn_home: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)
        btn_home=findViewById(R.id.btn_home)
        toolbar_finish_activity=findViewById(R.id.toolbar_finish_activity)
        setSupportActionBar(toolbar_finish_activity)
        val actionBar = supportActionBar
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
        toolbar_finish_activity.setNavigationOnClickListener{
            onBackPressed()
        }
        btn_home.setOnClickListener{
            finish()
        }
    }
    }
