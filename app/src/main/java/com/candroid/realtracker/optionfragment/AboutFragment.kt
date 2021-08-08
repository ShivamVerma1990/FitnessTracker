package com.candroid.realtracker.optionfragment

import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.candroid.realtracker.R
import com.candroid.realtracker.workout.MainActivity
import kotlinx.android.synthetic.main.fragment_about.*
import org.w3c.dom.Text

class AboutFragment : Fragment() {
    lateinit var imgLegWorkout: ImageView
lateinit var textL:TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_about, container, false)
textL=view.findViewById(R.id.textL)
        imgLegWorkout=view.findViewById(R.id.imgLegWorkout)
        textL.movementMethod = LinkMovementMethod.getInstance();

        imgLegWorkout.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)

            startActivity(intent)
        }

        return view
    }


}