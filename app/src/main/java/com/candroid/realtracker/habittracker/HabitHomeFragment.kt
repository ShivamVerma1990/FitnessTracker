package com.candroid.realtracker.habittracker


import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.candroid.realtracker.R
import com.candroid.realtracker.habittracker.habit_database.HabitDatabase
import com.candroid.realtracker.habittracker.habitrepo.HabitRepository
import com.candroid.realtracker.habittracker.habitrepo.ViewModelFactory
import com.candroid.realtracker.habittracker.ui.HabitViewModel
import com.candroid.realtracker.habittracker.util.RecyclerAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_habit_home.*


class HabitHomeFragment : Fragment(R.layout.fragment_habit_home) {
    lateinit var habitViewModel: HabitViewModel
    var habitList = listOf<Habit>()

    lateinit var fab: FloatingActionButton


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fab = view.findViewById(R.id.fab)

        fab.setOnClickListener {
            it.findNavController().navigate(R.id.action_habitHomeFragment_to_createNewHabitFragment)
        }
        val db = HabitDatabase(activity as Context)
        val habitRepository = HabitRepository(db)
        val factory = ViewModelFactory(habitRepository)
        habitViewModel = ViewModelProvider(this, factory).get(HabitViewModel::class.java)
        rv_habits.layoutManager = LinearLayoutManager(activity)
        val adapter = RecyclerAdapter(habitList)
        rv_habits.adapter = adapter
        habitViewModel.getAllHabit().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            adapter.setDate(it)
            habitList = it
            if (it.isEmpty()) {
                rv_habits.visibility = View.GONE

                tv_emptyView.visibility = View.VISIBLE
            } else {
                rv_habits.visibility = View.VISIBLE

                tv_emptyView.visibility = View.GONE

            }

        })

        swipeToRefresh.setOnClickListener {
            adapter.setDate(habitList)
            swipeToRefresh.isRefreshing = false


        }


    }



    }



