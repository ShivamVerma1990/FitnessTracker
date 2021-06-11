package com.candroid.realtracker.workout

import com.candroid.realtracker.R

class Exercises {
    companion object {
        fun defaultExerciseList():ArrayList<ExerciseModel>{
            val exerciseList = ArrayList<ExerciseModel>()
            val jumpingJacks = ExerciseModel(1, "Jumping Jacks", R.drawable.jumping_jacks, false, false)
            val wallSit = ExerciseModel(2, "Wall Sit", R.drawable.wall_sit, false, false)
            val pushUps = ExerciseModel(3, "Push Ups", R.drawable.high_knee, false, false)
            val crunch = ExerciseModel(4, "Crunch", R.drawable.crunch, false, false)
            val stepUps = ExerciseModel(5, "Step Up", R.drawable.step_up, false, false)
            val squats = ExerciseModel(6, "Squats", R.drawable.squat, false, false)
            val tricepDips = ExerciseModel(7, "Triceps Dip", R.drawable.triceps_dip, false, false)
            val plank = ExerciseModel(8, "Plank", R.drawable.plank, false, false)
            val highKnees = ExerciseModel(9, "High Knees", R.drawable.high_knee, false, false)
            val lunges = ExerciseModel(10, "Lunges", R.drawable.lunge, false, false)
            val pushUpWithRotation = ExerciseModel(11, "Push Up With Rotation", R.drawable.pushup_rotation, false, false)
            val sidePlank = ExerciseModel(12, "Side Plank", R.drawable.side_plank, false, false)

            exerciseList.add(jumpingJacks)
            exerciseList.add(wallSit)
            exerciseList.add(pushUps)
            exerciseList.add(crunch)
            exerciseList.add(stepUps)
            exerciseList.add(squats)
            exerciseList.add(tricepDips)
            exerciseList.add(plank)
            exerciseList.add(highKnees)
            exerciseList.add(lunges)
            exerciseList.add(pushUpWithRotation)
            exerciseList.add(sidePlank)
            return exerciseList
        }
    }
}