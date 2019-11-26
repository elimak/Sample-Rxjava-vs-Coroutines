package com.elimak.rxjava_coroutines_flow.di

import android.content.Context
import androidx.lifecycle.ViewModel
import com.elimak.rxjava_coroutines_flow.App
import com.elimak.rxjava_coroutines_flow.repository.CountryRepoFlow
import com.elimak.rxjava_coroutines_flow.repository.CountryRepoRx
import com.elimak.rxjava_coroutines_flow.ui.main.MainViewModel

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun inject(application: App)
    fun inject(viewModel: MainViewModel)

    fun inject(countryRepository: CountryRepoRx)
    fun inject(countryRepository: CountryRepoFlow)

    @AppContext
    fun getContext(): Context
}