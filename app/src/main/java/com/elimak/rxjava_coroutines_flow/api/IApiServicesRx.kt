package com.elimak.rxjava_coroutines_flow.api

import com.elimak.rxjava_coroutines_flow.db.country.Country
import io.reactivex.Flowable
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface IApiServicesRx {

    @GET("rest/v2/all")
    fun getAllCountries(): Flowable<List<Country>>

    companion object Factory {
        const val BASE_URL = "https://restcountries.eu/"

        fun create(): IApiServicesRx {
            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(IApiServicesRx::class.java)
        }
    }
}

