package com.kursor.myapplication.storage

import com.kursor.myapplication.storage.data.MessageStorage
import com.kursor.myapplication.storage.data.MessageStorageImpl
import com.russhwolf.settings.Settings
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

