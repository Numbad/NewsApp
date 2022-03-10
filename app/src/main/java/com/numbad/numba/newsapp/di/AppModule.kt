package com.numbad.numba.newsapp.di

import com.numbad.numba.newsapp.common.Constant
import com.numbad.numba.newsapp.data.remote.NewsApi
import com.numbad.numba.newsapp.data.repository.NewsRepositoryImpl
import com.numbad.numba.newsapp.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.Realm
import io.realm.RealmConfiguration
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NewsApi::class.java )
    }

    @Provides
    @Singleton
    fun provideNewsApiRepository(
        api: NewsApi,
        realm: Realm
    ): NewsRepository {
        return NewsRepositoryImpl(api, realm)
    }

    @Provides
    @Singleton
    fun providesRealmDatabase(): Realm {
        val config = RealmConfiguration.Builder()
            .name("myrealm.realm")
            .schemaVersion(0)
            .build()
        return Realm.getInstance(config)
    }
}