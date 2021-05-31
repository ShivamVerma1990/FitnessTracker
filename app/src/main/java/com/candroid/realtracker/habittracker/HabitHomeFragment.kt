package com.candroid.realtracker.habittracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.candroid.realtracker.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.tuann.floatingactionbuttonexpandable.FloatingActionButtonExpandable


class HabitHomeFragment : Fragment() {

lateinit var fab: FloatingActionButtonExpandable


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_habit_home, container, false)
        fab=view.findViewById(R.id.fab)
fab.setOnClickListener {
    it.findNavController().navigate(R.id.action_habitHomeFragment_to_createNewHabitFragment)
}
    return view
    }


}