package com.example.myapplication

import java.io.Serializable

data class UserInfo(
    var username : String,
    var password : String
) : Serializable
