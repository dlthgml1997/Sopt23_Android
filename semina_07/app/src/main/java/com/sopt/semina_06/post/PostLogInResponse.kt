package com.sopt.semina_06.post


data class PostLogInResponse(
    val status : Int,
    val massege : String,
    val data : LoginData
)

data class LoginData(
    val token : String
)