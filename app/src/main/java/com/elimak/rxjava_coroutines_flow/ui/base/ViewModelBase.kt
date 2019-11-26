package com.elimak.rxjava_coroutines_flow.ui.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

open class ViewModelBase(application: Application) : AndroidViewModel(application){

    protected var context: Application = application
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()
    protected val compositeJob = SupervisorJob()
    protected val viewModelScope = CoroutineScope(Dispatchers.Main + compositeJob)

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
        compositeJob.cancel()
    }
}