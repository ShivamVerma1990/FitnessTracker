package com.candroid.realtracker

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.candroid.realtracker.authantication.LoginActivity
import com.candroid.realtracker.chatbot.ChatActivity
import com.candroid.realtracker.fitInfo.FitnessActivity
import com.candroid.realtracker.habittracker.HabitActivity
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    lateinit var lAuth: FirebaseAuth
   lateinit var cardHabit:CardView
    lateinit var chatId: CardView
    lateinit var profile: CardView
    lateinit var fitVideo: CardView
    lateinit var drawerLayout: DrawerLayout

    lateinit var navigationView: NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        chatId = findViewById(R.id.chatId)
        cardHabit=findViewById(R.id.cardHabit)
        fitVideo = findViewById(R.id.fitVideo)
        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.navigationView)
        profile = findViewById(R.id.profile)
        lAuth = FirebaseAuth.getInstance()
        cardHabit.setOnClickListener {
            val intent = Intent(this, HabitActivity::class.java)
            startActivity(intent)
        }











        fitVideo.setOnClickListener {
            val intent = Intent(this, FitnessActivity::class.java)
            startActivity(intent)
        }


        chatId.setOnClickListener {
            val intent = Intent(this, ChatActivity::class.java)
            startActivity(intent)
        }
        profile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }


        setUpToolbar()
        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this@MainActivity,
            drawerLayout, R.string.open_drawer,
            R.string.close_drawer
        )
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val item = item.itemId

        if (item == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        when (item) {
            R.id.log_out -> {
                val dialog = AlertDialog.Builder(this)
                dialog.setTitle("Confirmation")
                dialog.setMessage("Are you sure!!")
                dialog.setPositiveButton("Ok") { _, _ ->
                    lAuth.signOut()
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.flags =
                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK //makesure user cant go back

                    startActivity(intent)
                }
                dialog.setNegativeButton("Cancel") { _, _ ->

                }
                    .create().show()
            }
        }
        return true
    }

    fun setUpToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "DASHBOARD"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


}
