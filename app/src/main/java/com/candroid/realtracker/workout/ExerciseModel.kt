package com.candroid.realtracker.workout

class ExerciseModel(private var id:Int, private var name:String,
                    private var image:Int, private var isComplete:Boolean, private var isSelected: Boolean) {

    fun getID():Int{
        return id
    }

    fun getName():String{
        return name
    }

    fun getImage():Int{
        return image
    }

    fun getIsCompleted():Boolean{
        return isComplete
    }

    fun getIsSelected():Boolean{
        return isSelected
    }

    //setters
    fun getID(newId: Int){
        id = newId
    }

    fun setName(newName: String){
        name = newName
    }

    fun setImage(newImage:Int){
        image = newImage
    }

    fun setIsCompleted(newCompleteStatus: Boolean){
        isComplete = newCompleteStatus
    }

    fun setIsSelected(newSelectionStatus: Boolean){
        isSelected = newSelectionStatus
    }
}

