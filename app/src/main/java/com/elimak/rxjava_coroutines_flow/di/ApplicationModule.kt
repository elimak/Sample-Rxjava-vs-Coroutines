package com.elimak.rxjava_coroutines_flow.di

import android.app.Application
import android.content.Context
import com.elimak.rxjava_coroutines_flow.App
import com.elimak.rxjava_coroutines_flow.repository.IRepository
import com.elimak.rxjava_coroutines_flow.repository.Repository

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
    protected fun provideRepository(): IRepository {
        return Repository(baseApp, EmptyCoroutineContext)
    }
}