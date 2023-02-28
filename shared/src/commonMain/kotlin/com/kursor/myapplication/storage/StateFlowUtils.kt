package com.kursor.myapplication.storage

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.*

interface Cancellable {
    fun cancel()
}

open class CStateFlow<T : Any>(origin: StateFlow<T>) : StateFlow<T> by origin {
    fun collect(block: (T) -> Unit): Cancellable {
        val coroutineScope = CoroutineScope(Dispatchers.Main.immediate)

        onEach {
            block(it)
        }.launchIn(coroutineScope)

        return object : Cancellable {
            override fun cancel() {
                coroutineScope.cancel()
            }
        }
    }
}

class CMutableStateFlow<T : Any>(
    private val origin: MutableStateFlow<T>
) : CStateFlow<T>(origin), MutableStateFlow<T> by origin {

    constructor(initialValue: T) : this(MutableStateFlow(initialValue))

    override val replayCache: List<T> = origin.replayCache
    override var value: T = origin.value

    override suspend fun collect(collector: FlowCollector<T>) = origin.collect(collector)

}