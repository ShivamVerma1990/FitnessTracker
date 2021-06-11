package com.candroid.realtracker.habittracker.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.candroid.realtracker.habittracker.Habit
import com.candroid.realtracker.habittracker.habitrepo.HabitRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HabitViewModel(private val repository: HabitRepository):ViewModel()  {
    fun insertHabit(habits: Habit) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertHabit(habits)
    }

    fun deleteHabit(habit: Habit) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteHabit(habit)
    }

    fun updateHabit(habit: Habit) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateHabit(habit)
    }

    fun getAllHabit() = repository.getAllHabit()
    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }
}
