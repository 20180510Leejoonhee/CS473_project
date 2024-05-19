package com.example.cs473

data class User(
    var name: String,
    var email: String,
    var uid: String
) {
    constructor(): this("", "", "")
}