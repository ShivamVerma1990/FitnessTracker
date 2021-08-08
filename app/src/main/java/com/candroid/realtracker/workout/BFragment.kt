package com.candroid.realtracker.workout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.candroid.realtracker.R

class BFragment : Fragment() {
    lateinit var imgBackBiceps:ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view= inflater.inflate(R.layout.fragment_b, container, false)
        imgBackBiceps=view.findViewById(R.id.imgBackBiceps)
        imgBackBiceps.setOnClickListener {
            findNavController().navigate(R.id.action_BFragment_to_optionsFragment)
        }
   return view;

    }

}