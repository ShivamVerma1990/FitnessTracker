package com.candroid.realtracker

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    lateinit var mAuth: FirebaseAuth
    private var firebaseDatabase: FirebaseDatabase? = null
    private var firebaseReference: DatabaseReference? = null
    var userId: String? = null
    val TAG="MY NAME"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        toolbar = findViewById(R.id.toolbar)
        setUpToolbar()
mAuth= FirebaseAuth.getInstance()
         firebaseDatabase=FirebaseDatabase.getInstance()
        firebaseReference=firebaseDatabase!!.getReference("Users")
        val user=FirebaseAuth.getInstance().currentUser
        userId=user?.uid
        firebaseReference!!.child(userId!!).addValueEventListener(object :ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                Log.d(TAG,"Failed to read user",error.toException())
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val users=snapshot.getValue(User::class.java)
                gender.text=users!!.gender
                profileEmail.text= users.email
                profileName.text= users.name
                profileAddress.text= users.address
                profileweight.text= users.weight



            }


        })

    }

    fun setUpToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "PROFILE "
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_l, R.anim.slide_out_r)
    }
}
