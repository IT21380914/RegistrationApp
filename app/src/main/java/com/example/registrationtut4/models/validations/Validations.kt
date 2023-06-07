package com.example.registrationtut4.models.validations

sealed class Validations{
    data class Empty(val errorMessage:String):Validations()
    data class Invalid(val errorMessage: String):Validations()
    object Valid:Validations()
}
