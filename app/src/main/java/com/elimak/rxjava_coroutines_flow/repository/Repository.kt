package com.elimak.rxjava_coroutines_flow.repository

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class Repository @Inject constructor(private var context: Context,
                                     override val coroutineContext: CoroutineContext
) : IRepository, CoroutineScope {
}