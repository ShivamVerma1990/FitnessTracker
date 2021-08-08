package com.candroid.realtracker.authantication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.candroid.realtracker.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forget.*
import kotlinx.android.synthetic.main.activity_ragister.*

class ForgetActivity : AppCompatActivity() {
lateinit var fEmail:EditText
    lateinit var button: Button
    lateinit var toolbar: Toolbar
    lateinit var fAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget)
  fAuth= FirebaseAuth.getInstance()
    fEmail=findViewById(R.id.inputEmailsf)
        button=findViewById(R.id.button)
        toolbar = findViewById(R.id.toolbar)
        setUpToolbar()
        button.setOnClickListener {
            val fEmail=inputEmailsf.text.toString().trim()
            if (fEmail.isEmpty()) {

                inputEmailsf.error = "email empty"
                inputEmailsf.requestFocus()
            }
            else if (!Patterns.EMAIL_ADDRESS.matcher(fEmail).matches()) {
                inputEmailsf.error = "Wrong email pattern"
                inputEmailsf.requestFocus()
            }
            else{
                fAuth.sendPasswordResetEmail(fEmail).addOnCompleteListener(this) {
                    if(it.isSuccessful){
                        Toast.makeText(this,"Check your email please", Toast.LENGTH_SHORT).show()

                    }
                    else{
                        Toast.makeText(this," Try Again!!something wrong", Toast.LENGTH_SHORT).show()

                    }

                }


            }



        }
    }
    fun setUpToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Back"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_l,R.anim.slide_out_r)
    }

}


