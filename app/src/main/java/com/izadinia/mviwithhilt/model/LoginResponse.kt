package com.izadinia.mviwithhilt.model



data class LoginResponse (
        var success:Boolean?,
        var message: ArrayList<String>,
        var data: ArrayList<Data>
    )

data class Data(
        val is_verified:Boolean?=null,
        val accessToken:String
    )

data class LoginWithEmailRequest(
        val email:String,
        val password:String
    )
