package com.candroid.realtracker.workout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import com.candroid.realtracker.R


class BackFragment : Fragment() {

lateinit var imgBackWorkout:ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_back, container, false)

        imgBackWorkout=view.findViewById(R.id.imgBackWorkout)
        imgBackWorkout.setOnClickListener {
            it.findNavController().navigate(R.id.action_backFragment_to_optionsFragment)
        }
        return view
    }

}