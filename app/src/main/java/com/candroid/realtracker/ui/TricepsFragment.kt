package com.candroid.realtracker.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import com.candroid.realtracker.R


class TricepsFragment : Fragment() {
    // TODO: Rename and change types of parameters
lateinit var imgBackTriceps:ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        val view=inflater.inflate(R.layout.fragment_triceps, container, false)
imgBackTriceps=view.findViewById(R.id.imgBackTriceps)
        imgBackTriceps.setOnClickListener {
   it.findNavController().navigate(R.id.action_tricepsFragment_to_optionsFragment)

        }


        return view
    }


}