package com.example.iicnmademo.di

import android.content.Context
import androidx.room.Room
import com.example.iicnmademo.data.local.IICnmaDemoDatabase
import com.example.iicnmademo.data.repository.home.HomeLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideMovieDatabase(@ApplicationContext context: Context): IICnmaDemoDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            IICnmaDemoDatabase::class.java, "movie.db"
        ).build()

    @Provides
    @Singleton
    fun providesPopularMoviesDataSource(
        database: IICnmaDemoDatabase,
    ): HomeLocalDataSource = database.recordDao()
}