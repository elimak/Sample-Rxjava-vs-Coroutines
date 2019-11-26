package com.elimak.rxjava_coroutines_flow

import android.app.Application
import com.elimak.rxjava_coroutines_flow.di.ApplicationComponent
import com.elimak.rxjava_coroutines_flow.di.ApplicationModule
import com.elimak.rxjava_coroutines_flow.di.DaggerApplicationComponent

class App: Application() {
    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        instance = this
        setup()
    }

    fun setup() {
        component = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
        component.inject(this)
    }

    fun getApplicationComponent(): ApplicationComponent {
        return component
    }

    companion object {
        lateinit var instance: App private set
    }
}
