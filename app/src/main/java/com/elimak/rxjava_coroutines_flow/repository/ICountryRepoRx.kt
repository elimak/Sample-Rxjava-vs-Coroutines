package com.elimak.rxjava_coroutines_flow.repository

import com.elimak.rxjava_coroutines_flow.db.country.Country
import io.reactivex.Flowable

interface ICountryRepoRx {
    fun fetchAllCountries(force: Boolean) : Flowable<List<Country>>
    fun getCountryById(id: Int) : Flowable<Country>
}
