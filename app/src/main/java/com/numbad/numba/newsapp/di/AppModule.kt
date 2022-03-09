package com.numbad.numba.newsapp.di

import com.numbad.numba.newsapp.common.Constant
import com.numbad.numba.newsapp.data.remote.NewsApi
import com.numbad.numba.newsapp.data.repository.NewsRepositoryImpl
import com.numbad.numba.newsapp.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideCookAppApi(): NewsApi {
        return Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NewsApi::class.java )
    }

    @Provides
    @Singleton
    fun provideDishRepository(api: NewsApi): NewsRepository {
        return NewsRepositoryImpl(api)
    }
}