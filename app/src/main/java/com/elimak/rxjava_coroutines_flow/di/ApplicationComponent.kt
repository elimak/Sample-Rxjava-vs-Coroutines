package com.elimak.rxjava_coroutines_flow.di

import android.content.Context
import androidx.lifecycle.ViewModel
import com.elimak.rxjava_coroutines_flow.App

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun inject(application: App)
    fun inject(viewModel: ViewModel)

    @AppContext
    fun getContext(): Context
}