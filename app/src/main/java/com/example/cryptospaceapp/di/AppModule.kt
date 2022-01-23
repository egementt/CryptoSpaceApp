package com.example.cryptospaceapp.di

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.example.cryptospaceapp.repository.CryptoRepository
import com.example.cryptospaceapp.repository.NewsRepository
import com.example.cryptospaceapp.service.network.CryptoService
import com.example.cryptospaceapp.service.network.NewsService
import com.example.cryptospaceapp.util.Constant
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
    @Singleton
    @Provides
    fun provideCryptoApi(): CryptoService{
        return Retrofit.Builder().baseUrl(Constant.BASE_URL_CRYPTO)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CryptoService::class.java)
    }

    @Singleton
    @Provides
    fun provideCryptoRepository(api: CryptoService) = CryptoRepository(api)

    @Singleton
    @Provides
    fun provideNewsApi(): NewsService {
        return Retrofit.Builder().baseUrl(Constant.BASE_URL_NEWS)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsService::class.java)
    }

    @Singleton
    @Provides
    fun provideNewsRepository(api: NewsService) = NewsRepository(api)


}