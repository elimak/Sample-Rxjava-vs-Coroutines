package com.elimak.rxjava_coroutines_flow.ui.main

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.elimak.rxjava_coroutines_flow.App
import com.elimak.rxjava_coroutines_flow.repository.ICountryRepoRx
import javax.inject.Inject

class MainViewModel : ViewModel() {
    @Inject
    lateinit var repository: ICountryRepoRx

    val text: ObservableField<String> = ObservableField("Main Fragment Title")

    init {
        App.instance.getApplicationComponent().inject(this)
    }
}
