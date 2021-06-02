package com.candroid.realtracker.habittracker.habitrepo

import com.candroid.realtracker.habittracker.Habit
import com.candroid.realtracker.habittracker.habit_database.HabitDatabase

class HabitRepository(private val db: HabitDatabase) {
    suspend fun insertHabit(habit: Habit)=db.getDao().insertHabit(habit)

    suspend fun deleteHabit(habit: Habit)=db.getDao().deleteHabit(habit)

    suspend fun updateHabit(habit: Habit)=db.getDao().updateHabit(habit)

    fun getAllHabit()=db.getDao().getAllHabit()

    suspend fun deleteAll()=db.getDao().deleteAll()


}