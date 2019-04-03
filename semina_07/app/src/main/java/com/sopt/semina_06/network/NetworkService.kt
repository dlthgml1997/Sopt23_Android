package com.sopt.semina_06.network

import com.google.gson.JsonObject
import com.sopt.semina_06.get.GetBoardListResponse
import com.sopt.semina_06.get.GetDetailedBoardResponse
import com.sopt.semina_06.post.PostLikedResponse
import com.sopt.semina_06.post.PostLogInResponse
import com.sopt.semina_06.post.PostSignUpResponse
import com.sopt.semina_06.post.postWriteBoardResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface NetworkService {
    //회원가입
    @POST("/users") // 어노테이션 (자바형식..? ), 타입형식("경로")
    fun PostSignUpResponse(
        @Header("Content-Type") content_type: String,
        @Body() body : JsonObject //요청방식 : foam 형식 (param형식일 때: @Field("id") id: String)
    ) : Call<PostSignUpResponse> //리턴 타입
    //로그인
    @POST("/login")
    fun postLoginResponse(
        @Header("Content-Type") content_type: String,
        @Body body : JsonObject
    )  : Call<PostLogInResponse>

    //글작성
    @Multipart
    @POST("/contents")
    fun postWriteBoardResponse(
        @Header("Authorization") token: String,
        @Part("title") title : RequestBody,
        @Part("contents") contents : RequestBody,
        @Part photo: MultipartBody.Part?
    ) :Call<postWriteBoardResponse>

    //글조회하기
    @GET("/contents")
    fun getBoardListResponse(
        @Header("Content-Type") content_type : String,
        @Query("offset") offset : Int,
        @Query("limit") limit : Int
    ) : Call<GetBoardListResponse>

    //게시글 상세조회
    @GET("/contents/{contentIdx}")
    fun getDetailedBoardResponse(
        @Header("Content-Type") content_type : String,
        @Header("Authorization") token : String,
        @Path("contentIdx") contentIdx : Int
    ) : Call<GetDetailedBoardResponse>

    //좋아요/취소
    @POST("/contents/{contentIdx}/likes")
    fun postLikedResponse(
        @Header("Content-Type") content_type: String,
        @Header("Application") token: String,
        @Path("contentIdx") contentIdx : Int
    ) :Call<PostLikedResponse>

}