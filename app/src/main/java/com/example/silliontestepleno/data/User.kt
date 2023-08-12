package com.example.silliontestepleno.data

data class User(

    val id: Int,
    val uid: String,
    val password: String,
    val first_name: String,
    val last_name: String,
    val username: String,
    val email: String,
    val avatar: String,
    val gender: String,
    val phone_number: String,
    val social_insurance_number: String,
    val date_of_birth: String,
    val employment: Employment,
    val coordinates: Coordinates,
    val address: Address,
    val subscription: Subscription,
    val creditCard: CreditCard

)
