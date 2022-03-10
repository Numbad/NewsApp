package com.numbad.numba.newsapp.di

import com.numbad.numba.newsapp.common.Constant
import com.numbad.numba.newsapp.data.remote.NewsApi
import com.numbad.numba.newsapp.data.repository.NewsRepositoryImpl
import com.numbad.numba.newsapp.domain.repository.NewsRepository
import com.numbad.numba.newsapp.domain.use_cases.GetArticleUseCase
import com.numbad.numba.newsapp.domain.use_cases.GetArticleUseCaseImpl
import com.numbad.numba.newsapp.domain.use_cases.GetNewsUseCase
import com.numbad.numba.newsapp.domain.use_cases.GetNewsUseCaseImpl
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
    fun provideArticleUseCase(
        repo: NewsRepository
    ): GetArticleUseCase {
        return GetArticleUseCaseImpl(repo)
    }

@Provides
    @Singleton
    fun provideNewsUseCase(
        repo: NewsRepository
    ): GetNewsUseCase {
        return GetNewsUseCaseImpl(repo)
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