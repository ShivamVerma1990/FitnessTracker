package com.candroid.realtracker.authantication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.candroid.realtracker.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forget.*
import kotlinx.android.synthetic.main.activity_ragister.*

class ForgetActivity : AppCompatActivity() {
lateinit var fEmail:EditText
    lateinit var button: Button
    lateinit var fAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget)
  fAuth= FirebaseAuth.getInstance()
    fEmail=findViewById(R.id.inputEmailsf)
        button=findViewById(R.id.button)
        button.setOnClickListener {
            val fEmail=inputEmailsf.text.toString()
            if (fEmail.isEmpty()) {

                inputEmails.error = "email empty"
                inputEmails.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(fEmail).matches()) {
                inputEmails.error = "Wrong email pattern"
                inputEmails.requestFocus()
                return@setOnClickListener
            }


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
    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_l,R.anim.slide_out_r)
    }

}


