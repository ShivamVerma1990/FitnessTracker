package com.candroid.realtracker.bmical

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.*
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.candroid.realtracker.R


class BmiActivity : AppCompatActivity() {
   lateinit var mcurrentheight: TextView
   lateinit var mcurrentweight: TextView
   lateinit var mcurrentage:TextView
   lateinit var mincrementage: ImageView
   lateinit var mdecrementage:ImageView
  lateinit  var mincrementweight:ImageView
   lateinit var mdecrementweight:ImageView
   lateinit var mseekbarforheight: SeekBar
  lateinit  var mcalculatebmi: Button
   lateinit var mmale: RelativeLayout
   lateinit var mfemale:RelativeLayout

    var intweight = 55
    var intage = 22
    var currentprogress = 0
    var mintprogress = "170"
    var typerofuser = "0"
    var weight2 = "55"
    var age2 = "22"

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi)
        supportActionBar?.hide()
        mcurrentage = findViewById(R.id.currentage)
        mcurrentweight = findViewById(R.id.currentweight)
        mcurrentheight = findViewById(R.id.currentheight)
        mincrementage = findViewById(R.id.incrementage)
        mdecrementage = findViewById(R.id.decrementage)
        mincrementweight = findViewById(R.id.incremetweight)
        mdecrementweight = findViewById(R.id.decrementweight)
        mcalculatebmi = findViewById(R.id.calculatebmi)
        mseekbarforheight = findViewById(R.id.seekbarforheight)
        mmale = findViewById(R.id.male)
        mfemale = findViewById<RelativeLayout>(R.id.female)
        mmale.setOnClickListener {
                mmale.background = ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.malefemalefocus
                )
            mfemale.background = ContextCompat.getDrawable(
                applicationContext,
                R.drawable.malefemalenotfocus
            )
                typerofuser = "Male"
            }

        mfemale.setOnClickListener{
                mfemale.background = ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.malefemalefocus
                )
            mmale.background = ContextCompat.getDrawable(
                applicationContext,
                R.drawable.malefemalenotfocus
            )
                typerofuser = "Female"
            }

        mseekbarforheight.max = 300
        mseekbarforheight.progress = 170
        mseekbarforheight.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                currentprogress = progress
                mintprogress = currentprogress.toString()
                mcurrentheight.text = mintprogress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
        mincrementweight.setOnClickListener{
                intweight += 1
                weight2 = intweight.toString()
            mcurrentweight.text = weight2
            }

        mincrementage.setOnClickListener{
                intage += 1
                age2 = intage.toString()
            mcurrentage.text = age2
            }

        mdecrementage.setOnClickListener{
                intage -= 1
                age2 = intage.toString()
                mcurrentage.setText(age2)
            }

        mdecrementweight.setOnClickListener{
                intweight -= 1
                weight2 = intweight.toString()
            mcurrentweight.text = weight2
            }

        mcalculatebmi.setOnClickListener{
                if (typerofuser == "0") {
                    Toast.makeText(
                        applicationContext,
                        "Select Your Gender First",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (mintprogress == "0") {
                    Toast.makeText(
                        applicationContext,
                        "Select Your Height First",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (intage == 0 || intage < 0) {
                    Toast.makeText(applicationContext, "Age is Incorrect", Toast.LENGTH_SHORT)
                        .show()
                } else if (intweight == 0 || intweight < 0) {
                    Toast.makeText(applicationContext, "Weight Is Incorrect", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    val intent = Intent(this, ResultOfBmiActivity::class.java)
                    intent.putExtra("gender", typerofuser)
                    intent.putExtra("height", mintprogress)
                    intent.putExtra("weight", weight2)
                    intent.putExtra("age", age2)
                    startActivity(intent)
                }
            }

    }
    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_l, R.anim.slide_out_r)
    }


}