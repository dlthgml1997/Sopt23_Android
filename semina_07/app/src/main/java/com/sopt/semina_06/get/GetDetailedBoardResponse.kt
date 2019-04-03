package com.sopt.semina_06.get

import com.sopt.semina_06.data.BoardData


data class GetDetailedBoardResponse(
    val status : Int,
    val message : String,
    val data : BoardData
)
