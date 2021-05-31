package com.candroid.realtracker

class User {
    lateinit var gender: String
    lateinit var email: String
    lateinit var name: String
    lateinit var address: String
    lateinit var weight: String

    constructor() {}
    constructor(gender:String,email:String,name:String,address:String,weight:String) {
        this.gender=gender
        this.email=email
        this.name=name
        this.address=address
        this.weight=weight

    }

}