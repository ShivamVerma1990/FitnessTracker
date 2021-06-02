package com.candroid.realtracker.habittracker

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "habit_table")
data class Habit(
    @PrimaryKey(autoGenerate = true)
    var id:Int=0,
    @ColumnInfo(name="habit_title") var  habit_title:String,
    @ColumnInfo(name="habit_des")  var habit_description:String,
    @ColumnInfo(name="habit_st")  var habit_startTime:String,
    @ColumnInfo(name="habit_image")   var imageId:Int
): Parcelable {


}