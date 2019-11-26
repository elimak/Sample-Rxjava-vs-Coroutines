package com.elimak.rxjava_coroutines_flow.api

import com.elimak.rxjava_coroutines_flow.db.country.Country
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface IApiServicesFlow {

    @GET("rest/v2/all")
    suspend fun getAllCountries(): List<Country>

    companion object Factory {
        const val BASE_URL = "https://restcountries.eu/"

        fun create(): IApiServicesFlow {
            val retrofit = retrofit2.Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(IApiServicesFlow::class.java)
        }
    }
}