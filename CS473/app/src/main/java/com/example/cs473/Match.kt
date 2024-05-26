package com.example.cs473

data class Match (
    var uid: User,
    var role: String,
    var topic: String,
) {
    constructor():this(User("", "", ""),"","")
}