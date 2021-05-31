package com.candroid.realtracker.authantication

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.candroid.realtracker.MainActivity
import com.candroid.realtracker.R
import com.candroid.realtracker.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_ragister.*

class RagisterActivity : AppCompatActivity() {

    lateinit var mAuth: FirebaseAuth
    private var firebaseDatabase: FirebaseDatabase? = null
    private var firebaseReference: DatabaseReference? = null
    lateinit var toolbar: Toolbar
    var userId: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ragister)
        toolbar = findViewById(R.id.toolbar)
        mAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()



        setUpToolbar()

        button.setOnClickListener {
            val rga = rg.checkedRadioButtonId
            val rb = findViewById<RadioButton>(rga)
            val rbText = rb.text
            val email = inputEmails.editText?.text.toString().trim()
            val name = inputName.editText?.text.toString().trim()
            val password = inputPassword.editText?.text.toString().trim()
            val location = inputLocation.editText?.text.toString().trim()
            val cPass = inputcPassword.editText?.text.toString().trim()
            val wt = inputWeight.editText?.text.toString()
            if (rbText.isEmpty()) {
                Toast.makeText(this, "please chose gender first", Toast.LENGTH_SHORT).show()
            } else if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

                inputEmails.error = "Invalid Email"
                inputEmails.requestFocus()
            } else if (name.isEmpty()) {
                inputName.error = "please fill this field"
                inputName.requestFocus()
                inputEmails.error = null
            } else if (password.isEmpty() || password.length <= 5) {


                inputPassword.error = "invalid password"
                inputPassword.requestFocus()
                inputName.error = null

            } else if (cPass.length != password.length || cPass.isEmpty()) {

                inputcPassword.error = "invalid matched"
                inputcPassword.requestFocus()
                inputPassword.error = null

            } else if (location.isEmpty()) {
                inputLocation.error = "invalid error"
                inputLocation.requestFocus()
                inputcPassword.error = null


            } else if (wt.isEmpty()) {
                inputWeight.error = "please fill this field"
                inputWeight.requestFocus()
                inputLocation.error = null

            } else {
                inputLocation.error = null

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) {
                    if (it.isSuccessful) {
                        firebaseReference= Firebase.database.getReference("Users")
                            //      firebaseReference = firebaseDatabase!!.getReference("users")
                        val userStatus = FirebaseAuth.getInstance().currentUser
                        userId = userStatus!!.uid
                        val myUser = User(rbText.toString(), email, name, location, wt)
                        firebaseReference!!.child(userId!!).setValue(myUser)
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                        Toast.makeText(this, "Successfully!!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show()
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
        overridePendingTransition(R.anim.slide_in_l, R.anim.slide_out_r)
    }


}


