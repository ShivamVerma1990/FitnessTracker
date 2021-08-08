package com.candroid.realtracker.workout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import com.candroid.realtracker.R

/**
 * A simple [Fragment] subclass.
 */
class BicepsFragment : Fragment() {
lateinit var imgBack:ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_biceps, container, false)
imgBack=view.findViewById(R.id.imgBack)
        imgBack.setOnClickListener {
            it.findNavController().navigate(R.id.action_bicepsFragment_to_optionsFragment)
        }
        return view

    }

}
