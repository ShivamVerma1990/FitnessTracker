package com.candroid.realtracker.optionfragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.candroid.realtracker.R
import com.candroid.realtracker.workout.MainActivity
import com.candroid.realtracker.workout.WorkoutActivity

class PolicyFragment : Fragment() {
lateinit var imgLegWorkout:ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view= inflater.inflate(R.layout.fragment_policy, container, false)
imgLegWorkout=view.findViewById(R.id.imgLegWorkout)
        imgLegWorkout.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)

            startActivity(intent)
        }

        return view


    }

}