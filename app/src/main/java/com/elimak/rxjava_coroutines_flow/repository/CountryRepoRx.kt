package com.elimak.rxjava_coroutines_flow.repository

import android.content.Context
import com.elimak.rxjava_coroutines_flow.App
import com.elimak.rxjava_coroutines_flow.api.IApiServicesRx
import com.elimak.rxjava_coroutines_flow.db.AppDatabase
import com.elimak.rxjava_coroutines_flow.db.country.Country
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CountryRepoRx @Inject constructor(context: Context) : ICountryRepoRx {
    @Inject lateinit var api: IApiServicesRx
    private var database: AppDatabase

    init {
        App.instance.getApplicationComponent().inject(this)
        database = AppDatabase.getDatabase(context)
    }

    private fun loadCountriesFromRemote() : Flowable<List<Country>> {
        // Load from API
        return api.getAllCountries()
            .flatMap { it ->
                // delete all records
                database.countryDao().deleteAllCountries().andThen(Flowable.just(it))
            }
            .flatMap {
                // insert newly loaded records
                database.countryDao().insertAll(it).andThen(Flowable.just(it))
            }
            .flatMap {
                // fetch records from DB
                database.countryDao().getAll()
                    .toFlowable()
            }
    }

    // ------------ //
    // Public
    // ------------ //

    override fun getCountryById(id: Int): Flowable<Country> {
        return database.countryDao().getCountryById(id)
            .subscribeOn(Schedulers.io())
            .toFlowable()
    }

    override fun fetchAllCountries(force: Boolean) : Flowable<List<Country>> {
        // Using force will refresh the data from online
        return database.countryDao().getAll()
            .subscribeOn(Schedulers.io())
            .toFlowable()
            .flatMap {
                if(it.isEmpty() || force) {
                    loadCountriesFromRemote()
                } else {
                    Flowable.just(it)
                }
            }
    }
}