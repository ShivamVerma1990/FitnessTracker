package com.candroid.realtracker.habittracker.habitrepo

import com.candroid.realtracker.habittracker.ui.HabitViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(
    private val repository: HabitRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HabitViewModel(repository) as T
    }
}