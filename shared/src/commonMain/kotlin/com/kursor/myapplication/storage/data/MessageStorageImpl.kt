package com.kursor.myapplication.storage.data

import com.kursor.myapplication.storage.domain.Message
import com.russhwolf.settings.Settings

class MessageStorageImpl(
    private val regularSettings: Settings,
    private val encryptedSettings: Settings
) : MessageStorage {

    override fun saveMessageToRegularSettings(message: Message) {
        regularSettings.putString(KEY_MESSAGE, message.text)
    }

    override fun getMessageFromRegularSettings(): Message? {
        val text = regularSettings.getStringOrNull(KEY_MESSAGE) ?: return null
        return Message(text)
    }

    override fun saveMessageToEncryptedSettings(message: Message) {
        encryptedSettings.putString(KEY_MESSAGE, message.text)
    }

    override fun getMessageFromEncryptedSettings(): Message? {
        val text = encryptedSettings.getStringOrNull(KEY_MESSAGE) ?: return null
        return Message(text)
    }

    private companion object {
        private const val KEY_MESSAGE = "key_message"
    }

}