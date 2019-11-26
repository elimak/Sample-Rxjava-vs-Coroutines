package com.elimak.rxjava_coroutines_flow.di

import android.app.Application
import android.content.Context
import com.elimak.rxjava_coroutines_flow.App
import com.elimak.rxjava_coroutines_flow.api.IApiServicesFlow
import com.elimak.rxjava_coroutines_flow.api.IApiServicesRx
import com.elimak.rxjava_coroutines_flow.repository.CountryRepoFlow
import com.elimak.rxjava_coroutines_flow.repository.ICountryRepoRx
import com.elimak.rxjava_coroutines_flow.repository.CountryRepoRx
import com.elimak.rxjava_coroutines_flow.repository.ICountryRepoFlow

import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import kotlin.coroutines.EmptyCoroutineContext

@Module
class ApplicationModule(private val baseApp: App) {

    @Provides
    @AppContext
    fun provideContext(): Context = baseApp.applicationContext

    @Provides
    @AppContext
    fun provideApplication(): Application = baseApp

    @Provides
    @Singleton
    protected fun provideCountryRepoRx(): ICountryRepoRx {
        return CountryRepoRx(baseApp)
    }

    @Provides
    @Singleton
    protected fun provideCountryRepoFlow(): ICountryRepoFlow {
        return CountryRepoFlow(baseApp, EmptyCoroutineContext)
    }

    @Provides
    fun provideApiServiceRx(): IApiServicesRx {
        return IApiServicesRx.create()
    }

    @Provides
    fun provideApiServicesFlow(): IApiServicesFlow {
        return IApiServicesFlow.create()
    }
}