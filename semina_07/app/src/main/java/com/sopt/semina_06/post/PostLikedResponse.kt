package com.sopt.semina_06.post

import com.sopt.semina_06.data.BoardData


data class PostLikedResponse(
    val status : Int,
    val message : String,
    val data : BoardData
)
