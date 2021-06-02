package com.candroid.realtracker.habittracker.habit_database

import com.candroid.realtracker.habittracker.Habit
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface HabitDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)

    suspend fun insertHabit(habit: Habit)
    @Delete
    suspend fun deleteHabit(habit: Habit)
    @Update
    suspend fun updateHabit(habit: Habit)

    @Query("SELECT*FROM habit_table ORDER BY id DESC")
    fun getAllHabit():LiveData<List<Habit>>
    @Query("DELETE FROM habit_table")
    suspend fun deleteAll()
}