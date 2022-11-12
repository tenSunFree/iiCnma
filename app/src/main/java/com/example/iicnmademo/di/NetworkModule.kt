package com.example.iicnmademo.di

import com.example.iicnmademo.BuildConfig
import com.example.iicnmademo.data.remote.API_BASE_URL
import com.example.iicnmademo.data.remote.home.HomeService
import com.example.iicnmademo.data.repository.home.HomeRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {

        val apiKeyInterceptor: (Interceptor.Chain) -> Response = { chain ->
            val originalRequest = chain.request()
            val originalUrl = originalRequest.url
            val newUrl: HttpUrl = originalUrl.newBuilder()
                .addQueryParameter("apikey", BuildConfig.API_KEY)
                .build()
            val newRequest = originalRequest.newBuilder()
                .url(newUrl)
                .build()
            chain.proceed(newRequest)
        }

        return Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(
                        HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        }
                    )
                    .addInterceptor(apiKeyInterceptor)
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesPopularMoviesRemoteDataSource(
        retrofit: Retrofit,
    ): HomeRemoteDataSource = retrofit.create(HomeService::class.java)
}