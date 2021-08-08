package com.candroid.realtracker.habittracker.util

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.candroid.realtracker.workout.MainActivity
import com.candroid.realtracker.R
import com.candroid.realtracker.intro.IntroActivity
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {
   lateinit var mAuth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        mAuth= FirebaseAuth.getInstance()
        if (mAuth.currentUser!=null) {
            Handler().postDelayed({



                val intent= Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)

                finish()
            },2000)

        }
       else{
            Handler().postDelayed({



                val intent= Intent(this@SplashActivity,IntroActivity::class.java)
                startActivity(intent)

                finish()
            },2000)


       }

    }
}