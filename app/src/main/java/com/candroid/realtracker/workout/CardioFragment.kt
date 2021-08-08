package com.candroid.realtracker.workout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import com.candroid.realtracker.R

class CardioFragment : Fragment() {
lateinit var imgCardioWorkout:ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_cardio, container, false)
        imgCardioWorkout=view.findViewById(R.id.imgCardioWorkout)
        imgCardioWorkout.setOnClickListener {
            it.findNavController().navigate(R.id.action_cardioFragment_to_optionsFragment)
        }
    return view
    }


}