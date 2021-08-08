package com.candroid.realtracker.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import com.candroid.realtracker.R

class SolderFragment : Fragment() {
lateinit var imgBackSolder:ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_solder, container, false)
        imgBackSolder=view.findViewById(R.id.imgBackSolder)
        imgBackSolder.setOnClickListener{
            imgBackSolder.setOnClickListener {
                it.findNavController().navigate(R.id.action_solderFragment_to_optionsFragment)
            }


        }
return view
    }


}