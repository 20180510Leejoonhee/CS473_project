package com.example.cs473

data class Message (
    var message: String?,
    var sendId: String?
) {
    constructor():this("","")
}