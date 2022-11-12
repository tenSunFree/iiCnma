package com.example.iicnmademo.data.remote.home

import com.example.iicnmademo.data.repository.home.HomeRemoteDataSource
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeService : HomeRemoteDataSource {

    @GET("object")
    override suspend fun getObject(
        @Query("size") size: Int,
        @Query("page") page: Int
    ): HomeObjectResponse
}