package com.example.cryptospaceapp.service.network

import com.example.cryptospaceapp.model.crypto_model.CryptoModel
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface CryptoService {

    @GET("coins/markets")
    suspend fun listCurrencies(
        @Query("vs_currency") vs_currency: String? = "usd",
        @Query("order") order: String? = "gecko_desc",
        @Query("per_page") per_page: Int?= 25,
        @Query("page") page: Int? = 1
    ): CryptoModel
}