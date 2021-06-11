package com.candroid.realtracker.bmical

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Html
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.candroid.realtracker.R


@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS", "DEPRECATION")
class ResultOfBmiActivity : AppCompatActivity() {

    lateinit var mbmidisplay: TextView

    //        lateinit var magedisplay:TextView
//        lateinit var mweightdisplay:TextView
//        lateinit var mheightdisplay:TextView
    lateinit var mbmicategory: TextView
    lateinit var mgender: TextView
    lateinit var mgotomain: Button
    var intents: Intent? = null

    lateinit var mimageview: ImageView
    var mbmi: String? = null
    var cateogory: String? = null
    var intbmi = 0f


    var intheight = 0f
    var intweight: kotlin.Float = 0f

    lateinit var mbackground: RelativeLayout


    @SuppressLint("ResourceAsColor", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_of_bmi)
        supportActionBar?.elevation = 0f
        val colorDrawable = ColorDrawable(Color.parseColor("#1E1D1D"))
        supportActionBar?.setBackgroundDrawable(colorDrawable)

        ///   ColorDrawable colorDrawable2=new ColorDrawable(Color.parseColor("#1E1D1D"));
        //      getSupportActionBar().setBackgroundDrawable(colorDrawable);


        ///   ColorDrawable colorDrawable2=new ColorDrawable(Color.parseColor("#1E1D1D"));
        //      getSupportActionBar().setBackgroundDrawable(colorDrawable);
        supportActionBar?.title = Html.fromHtml("<font color=\"white\"></font>")
        supportActionBar?.title = "Result"


        intents = getIntent()
        mbmidisplay = findViewById(R.id.bmidisplay)
        //    magedisplay=findViewById(R.id.agedisplay);
        //    mweightdisplay=findViewById(R.id.weightdisplay);
        //    magedisplay=findViewById(R.id.agedisplay);
        //    mweightdisplay=findViewById(R.id.weightdisplay);
        mbmicategory = findViewById(R.id.bmicategorydispaly)
        mgotomain = findViewById(R.id.gotomain)

        mimageview = findViewById(R.id.imageview)

        //   mheightdisplay=findViewById(R.id.heightdisplay);

        //   mheightdisplay=findViewById(R.id.heightdisplay);
        mgender = findViewById(R.id.genderdisplay)
        mbackground = findViewById(R.id.contentlayout)


        val height = intent.getStringExtra("height")
        val weight = intent.getStringExtra("weight")


        intheight = height.toFloat()
        intweight = weight.toFloat()

        intheight /= 100
        intbmi = intweight / (intheight * intheight)


        mbmi = intbmi.toString()
        println(mbmi)

        if (intbmi < 16) {
            mbmicategory.setText("Severe Thinness")
            //   mbackground.setBackgroundColor(Color.GRAY);
            mbackground.setBackgroundColor(Color.RED)
            mimageview.setImageResource(R.drawable.crosss)
            //  mimageview.setBackground(colorDrawable2);
        } else if (intbmi < 16.9 && intbmi > 16) {
            mbmicategory.setText("Moderate Thinness")
            mbackground!!.setBackgroundColor(R.color.halfwarn)
            mimageview.setImageResource(R.drawable.warning)
            //   mimageview.setBackground(colorDrawable2);
        } else if (intbmi < 18.4 && intbmi > 17) {
            mbmicategory.setText("Mild Thinness")
            mbackground!!.setBackgroundColor(R.color.halfwarn)
            mimageview.setImageResource(R.drawable.warning)
            //   mimageview.setBackground(colorDrawable2);
        } else if (intbmi < 24.9 && intbmi > 18.5) {
            mbmicategory.setText("Normal")
            mimageview.setImageResource(R.drawable.ok)
            // mbackground.setBackgroundColor(Color.YELLOW);
            //  mimageview.setBackground(colorDrawable2);
        } else if (intbmi < 29.9 && intbmi > 25) {
            mbmicategory.setText("Overweight")
            mbackground.setBackgroundColor(R.color.halfwarn)
            mimageview.setImageResource(R.drawable.warning)
            //mimageview.setBackground(colorDrawable2);
        } else if (intbmi < 34.9 && intbmi > 30) {
            mbmicategory.setText("Obese Class I")
            mbackground.setBackgroundColor(R.color.halfwarn)
            mimageview.setImageResource(R.drawable.warning)
            //  mimageview.setBackground(colorDrawable2);
        } else {
            mbmicategory.setText("Obese Class II")
            mbackground.setBackgroundColor(R.color.warn)
            mimageview.setImageResource(R.drawable.crosss)
            //  mimageview.setBackground(colorDrawable2);
        }

        //magedisplay.setText("your age is"+intent.getStringExtra("age"));
        //mheightdisplay.setText("Your Height is "+intent.getStringExtra("height"));
        //mweightdisplay.setText("Your Weight is "+intent.getStringExtra("weight"));

        //magedisplay.setText("your age is"+intent.getStringExtra("age"));
        //mheightdisplay.setText("Your Height is "+intent.getStringExtra("height"));
        //mweightdisplay.setText("Your Weight is "+intent.getStringExtra("weight"));
        mgender.text = intent.getStringExtra("gender")
        mbmidisplay.text = mbmi


        mgotomain.setOnClickListener {
            val intent1 = Intent(applicationContext, BmiActivity::class.java)
            startActivity(intent1)
        }


    }
}