package com.elimak.rxjava_coroutines_flow.ui.main

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.elimak.rxjava_coroutines_flow.App
import com.elimak.rxjava_coroutines_flow.db.country.Country
import com.elimak.rxjava_coroutines_flow.repository.ICountryRepoFlow
import com.elimak.rxjava_coroutines_flow.repository.ICountryRepoRx
import com.elimak.rxjava_coroutines_flow.ui.base.ViewModelBase
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MainViewModel(application: Application) : ViewModelBase(application) {
    @Inject lateinit var repositoryRx: ICountryRepoRx
    @Inject lateinit var repositoryFlow: ICountryRepoFlow

    val text: ObservableField<String> = ObservableField("Main Fragment Title")
    val loading: ObservableBoolean = ObservableBoolean(false)

    init {
        App.instance.getApplicationComponent().inject(this)
    }

    fun loadDataViaRx() {
        loading.set(true)
        compositeDisposable.add(
            repositoryRx.fetchAllCountries(true)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result ->
                        text.set("We loaded "+ result?.size.toString()+ " countries via RX java")
                        loading.set(false)
                    },{ failed ->
                        Toast.makeText(context,failed.message, Toast.LENGTH_SHORT).show()
                    })
        )
    }

    fun loadDataViaCoroutines() {
        viewModelScope.launch {
            loading.set(true)
            try {
                val result: List<Country> = repositoryFlow.fetchAllCountries(true)
                text.set("We loaded "+ result?.size.toString()+ " countries via Flow / Coroutines")
            } catch (failed: Exception) {
                Toast.makeText(context, failed.message, Toast.LENGTH_SHORT).show()
            }
            loading.set(false)
        }
        Log.d("val", "Coroutines")
    }
}
