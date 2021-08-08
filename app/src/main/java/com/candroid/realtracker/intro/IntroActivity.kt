package com.candroid.realtracker.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.candroid.realtracker.workout.MainActivity
import com.candroid.realtracker.R
import com.candroid.realtracker.authantication.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_intro.*

class IntroActivity : AppCompatActivity() {
    lateinit var adapter: ViewPagerHolder
lateinit var iAuth: FirebaseAuth
    val list = arrayListOf<IntroRec>(
        IntroRec(
            "100% Safe & Secure",
            R.drawable.ic_undraw_private_data_re,
            "Use on multiple,Setup app lock"
        ),
        IntroRec(
            "Working Out",
            R.drawable.ic_undraw_working_out,
            "This application provide all kind of features for improve your fitness"
        ),
        IntroRec(
            "Fitness Tracker",
            R.drawable.ic_undraw_fitness_tracker,
            "Here you can easily track your fitness related information"
        ),
        IntroRec
            (
            "GYM & Tracking",
            R.drawable.ic_undraw_activity_tracker_re,
            "This app help to record all kind of fitness related information"
        )
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        adapter = ViewPagerHolder(this, list)
        viewPage.adapter = adapter
        viewPage.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        iAuth= FirebaseAuth.getInstance()
        if (iAuth.currentUser!=null){
            Toast.makeText(this,"User is already logged in!!",Toast.LENGTH_SHORT).show()
        redirect("MAIN")
        }
        button.setOnClickListener {
            redirect("LOGIN")

        }
        setIndicater()
        setCurrentIndicater(0)
        viewPage.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                setCurrentIndicater(position)
                super.onPageSelected(position)

            }
        })
    }

    fun redirect(name:String){
        val intent=when(name){
            "LOGIN"->Intent(this,LoginActivity::class.java)
"MAIN"-> Intent(this, MainActivity::class.java)
 else-> throw Exception("no path exists")
        }
startActivity(intent)
        overridePendingTransition(R.anim.slide_in_r,R.anim.slide_out_l)
    finish()
    }
    private fun setCurrentIndicater(index: Int) {
        val childCount = llv.childCount
        for (i in 0 until childCount) {
            val imageView = llv[i] as ImageView
            if (i == index) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(applicationContext, R.drawable.indicater_active)
                )

            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(applicationContext, R.drawable.indicater_deactive)
                )
            }
        }
    }
    private fun setIndicater() {
        val indicater = arrayOfNulls<ImageView>(adapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        layoutParams.setMargins(8, 0, 0, 8)
        for (i in indicater.indices) {
            indicater[i] = ImageView(applicationContext)
            indicater[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(applicationContext, R.drawable.indicater_active)
                )
                this?.layoutParams = layoutParams
                llv.addView(indicater[i])
            }
        }
    }
}
