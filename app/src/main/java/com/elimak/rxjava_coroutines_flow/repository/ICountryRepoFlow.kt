package com.elimak.rxjava_coroutines_flow.repository

import com.elimak.rxjava_coroutines_flow.db.country.Country
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow

interface ICountryRepoFlow {
    suspend fun fetchAllCountries(force: Boolean) : List<Country>
    suspend fun getCountryById(id: Int) : Flow<Country>
}