package com.candroid.realtracker.authantication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.EditText

import android.widget.ImageView
import android.widget.Toast
import com.candroid.realtracker.MainActivity
import com.candroid.realtracker.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.core.view.View
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.view.*
import kotlinx.android.synthetic.main.activity_ragister.*
import kotlinx.android.synthetic.main.activity_ragister.button
import kotlinx.android.synthetic.main.activity_ragister.inputEmails

class LoginActivity : AppCompatActivity() {

 lateinit var lAuth: FirebaseAuth
lateinit var img:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        textReg.setOnClickListener {
            val intent=Intent(this,RagisterActivity::class.java)

            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_r,R.anim.slide_out_l)


        }

        textPass.setOnClickListener {
            val intent=Intent(this,ForgetActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_r,R.anim.slide_out_l)

        }
        lAuth= FirebaseAuth.getInstance()


        button.setOnClickListener {

            if(validateNumber()) {
                val email = inputEmailsl.editText?.text.toString().trim()
                val pass = editLock.editText?.text.toString().trim()
button.visibility=android.view.View.INVISIBLE
                lAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
                    if (it.isSuccessful) {
                       button.visibility=android.view.View.VISIBLE

                        Toast.makeText(this, "LOADING......", Toast.LENGTH_SHORT).show()

                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        button.visibility=android.view.View.VISIBLE

                        Toast.makeText(this, "INVALID PASSWORD OR EMAIL", Toast.LENGTH_SHORT).show()

                    }

                }
            }
        }
    }
    fun validateNumber(): Boolean {
        val email = inputEmailsl.editText?.text.toString().trim()
        val pass = editLock.editText?.text.toString().trim()

        if (email.isEmpty()||!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

            inputEmailsl.error = "Invalid Email"
            inputEmailsl.requestFocus()
            return false
        }
        else{
            inputEmailsl.error = null

        }

        if (pass.isEmpty()||pass.length <= 5) {
            editLock.error = "password invalid"
            editLock.requestFocus()
        return false
        }
else{
            editLock.error = null
return true
        }
    }
    }

