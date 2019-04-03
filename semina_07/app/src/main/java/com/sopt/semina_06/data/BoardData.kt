package com.sopt.semina_06.data

data class BoardData(
    val b_id : Int,
    val b_title : String,
    val b_contents : String,
    val b_date : String,
    val u_id : Int,
    var b_like : Int,
    val b_photo : String,
    val auth : Boolean,
    var like : Boolean
)