package com.example.iicnmademo.data.repository.home

import com.example.iicnmademo.data.remote.home.HomeObjectResponse

interface HomeRemoteDataSource {
    suspend fun getObject(size: Int, page: Int): HomeObjectResponse
}
