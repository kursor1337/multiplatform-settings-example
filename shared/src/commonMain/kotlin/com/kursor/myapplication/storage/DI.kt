package com.kursor.myapplication.storage

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DefaultComponentContext
import com.kursor.myapplication.storage.data.MessageStorage
import com.kursor.myapplication.storage.data.MessageStorageImpl
import com.kursor.myapplication.storage.ui.MessageComponent
import com.kursor.myapplication.storage.ui.RealMessageComponent
import com.russhwolf.settings.Settings
import org.koin.core.Koin
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.dsl.module

val storageModule = module {
    single<MessageStorage> { MessageStorageImpl(
        regularSettings = Settings(),
        encryptedSettings = createSettings(
            name = "encrypted_message_storage",
            encrypted = true
        )
    ) }
}
object IosComponentFactory : KoinComponent {

    private val koin = Koin().apply {
        loadModules(listOf(storageModule))
    }
    override fun getKoin(): Koin {
        return koin
    }

    fun createMessageComponent(componentContext: ComponentContext): MessageComponent {
        return RealMessageComponent(componentContext, get())
    }

}
