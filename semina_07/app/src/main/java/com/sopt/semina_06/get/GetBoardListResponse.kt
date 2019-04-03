package com.sopt.semina_06.get

import com.sopt.semina_06.data.BoardData

data class GetBoardListResponse(
    val status : Int,
    val message : String,
    val data : ArrayList<BoardData>
)
