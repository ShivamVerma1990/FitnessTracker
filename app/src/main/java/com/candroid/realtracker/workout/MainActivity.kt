package com.candroid.realtracker.workout


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import com.candroid.realtracker.R
import com.candroid.realtracker.adapter.SliderAdapter
import com.candroid.realtracker.articales.ArticlesActivity
import com.candroid.realtracker.articales.CovidActivity
import com.candroid.realtracker.authantication.LoginActivity
import com.candroid.realtracker.bmical.BmiActivity
import com.candroid.realtracker.chatbot.ChatActivity
import com.candroid.realtracker.fitInfo.FitnessActivity
import com.candroid.realtracker.habittracker.HabitActivity
import com.candroid.realtracker.optionfragment.AboutActivity
import com.candroid.realtracker.optionfragment.PolActivity
import com.candroid.realtracker.optionfragment.TActivity
import com.candroid.realtracker.stepCounter.StepActivity
import com.google.firebase.auth.FirebaseAuth
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    lateinit var cardCovid: CardView

    lateinit var lAuth: FirebaseAuth
    lateinit var cardHabit: CardView
    lateinit var chatId: CardView

    lateinit var cardArticles: CardView
    lateinit var cardWorkout: CardView
    lateinit var cardRun: CardView
    lateinit var profile: CardView
    lateinit var bmi: CardView
    lateinit var fitVideo: CardView

    lateinit var progressBar: ProgressBar
    lateinit var progress: RelativeLayout

    lateinit var sliderView: SliderView
    var images = intArrayOf(
        R.drawable.covida_img,
        R.drawable.oip,
        R.drawable.covidb_img,
        R.drawable.covidc_img,
        R.drawable.covide_img,
        R.drawable.covidr_img
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showView()

        cardClicks()
        setUpToolbar()
        R.string.close_drawer


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val item = item.itemId


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
            R.id.about -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_r, R.anim.slide_out_l)
            }
            R.id.policy -> {

                val intent = Intent(this, PolActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_r, R.anim.slide_out_l)
            }


        }
        return true
    }

    fun setUpToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "DASHBOARD"
    }

    fun showView() {
        progressBar = findViewById(R.id.progressBar)
        sliderView = findViewById(R.id.s)

        progress = findViewById(R.id.progress)
        progressBar.visibility = View.VISIBLE
        progress.visibility = View.VISIBLE
        bmi = findViewById(R.id.bmi)
        cardArticles = findViewById(R.id.cardArticles)
        cardRun = findViewById(R.id.cardRun)
        cardCovid = findViewById(R.id.cardCovid)

        cardWorkout = findViewById(R.id.cardWorkout)

    }

    fun cardClicks() {
        cardWorkout.setOnClickListener {


            val intent = Intent(this, WorkoutActivity::class.java)
            startActivity(intent)

            overridePendingTransition(R.anim.slide_in_r, R.anim.slide_out_l)

        }
        cardCovid.setOnClickListener {
            val intent = Intent(this, CovidActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_r, R.anim.slide_out_l)

        }

        cardRun.setOnClickListener {
            val intent = Intent(this, StepActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_r, R.anim.slide_out_l)


        }

        val sliderAdapter = SliderAdapter(images)

        sliderView.setSliderAdapter(sliderAdapter)
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM)
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION)
        sliderView.startAutoCycle()




        cardArticles.setOnClickListener {

            val intent = Intent(this, ArticlesActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_r, R.anim.slide_out_l)

        }

        CoroutineScope(Dispatchers.Main).launch {
            delay(3000)
            progressBar.visibility = View.GONE
            progress.visibility = View.GONE
        }

        toolbar = findViewById(R.id.toolbar)
        chatId = findViewById(R.id.chatId)

        cardHabit = findViewById(R.id.cardHabit)

        fitVideo = findViewById(R.id.fitVideo)

        profile = findViewById(R.id.profile)
        lAuth = FirebaseAuth.getInstance()

        bmi.setOnClickListener {

            val intent = Intent(this, BmiActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_r, R.anim.slide_out_l)


        }
        cardHabit.setOnClickListener {
            val intent = Intent(this, HabitActivity::class.java)

            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_r, R.anim.slide_out_l)

        }









        fitVideo.setOnClickListener {
            val intent = Intent(this, FitnessActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_r, R.anim.slide_out_l)

        }


        chatId.setOnClickListener {
            val intent = Intent(this, ChatActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_r, R.anim.slide_out_l)

        }
        profile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_r, R.anim.slide_out_l)

        }


    }
}
