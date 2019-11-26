package com.elimak.rxjava_coroutines_flow.repository

import android.content.Context
import com.elimak.rxjava_coroutines_flow.App
import com.elimak.rxjava_coroutines_flow.api.IApiServicesFlow
import com.elimak.rxjava_coroutines_flow.db.AppDatabase
import com.elimak.rxjava_coroutines_flow.db.country.Country
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
class CountryRepoFlow @Inject constructor(private var context: Context,
                                          override val coroutineContext: CoroutineContext
) : ICountryRepoFlow, CoroutineScope {

    @Inject lateinit var api: IApiServicesFlow
    private var database: AppDatabase

    init {
        App.instance.getApplicationComponent().inject(this)
        database = AppDatabase.getDatabase(context)
    }

    override suspend fun fetchAllCountries(force: Boolean): List<Country> {
        return coroutineScope {
            withContext(Dispatchers.IO) {
                // check if we have the data in the DB
                var list: List<Country> = database.countryDaoFlow().getAll()

                // if we don't or if we want to refresh the data with the online source
                if(list.isEmpty() || force) {
                    val countries = api.getAllCountries()
                    if(countries.isNotEmpty()) {
                        // clear the db, insert new data, return data inserted
                        database.countryDaoFlow().deleteAllCountries()
                        database.countryDaoFlow().insertAll(countries)
                        list = database.countryDaoFlow().getAll()
                    }
                }
                list
            }
        }
    }

    override suspend fun getCountryById(id: Int): Flow<Country> {
        return withContext(Dispatchers.IO) { database.countryDaoFlow().getCountryById(id) }
    }
}