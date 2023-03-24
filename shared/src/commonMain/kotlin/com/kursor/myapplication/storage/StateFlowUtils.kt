package com.kursor.myapplication.storage

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.*

interface Cancellable {
    fun cancel()
}

open class CStateFlow<T : Any>(origin: StateFlow<T>) : StateFlow<T> by origin

class CMutableStateFlow<T : Any>(
    private val origin: MutableStateFlow<T>
) : CStateFlow<T>(origin), MutableStateFlow<T> by origin {

    constructor(initialValue: T) : this(MutableStateFlow(initialValue))
    override suspend fun collect(collector: FlowCollector<T>) = origin.collect(collector)
}

open class FlowWrapper<T>(
    private val flow: Flow<T>
) {

    fun collect(consumer: (T) -> Unit): Cancellable {
        val scope = CoroutineScope(Dispatchers.Main.immediate)

        flow
            .onEach { consumer(it) }
            .launchIn(scope)

        return object : Cancellable {
            override fun cancel() {
                scope.cancel()
            }
        }
    }
}