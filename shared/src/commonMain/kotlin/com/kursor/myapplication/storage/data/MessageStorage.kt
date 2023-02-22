package com.kursor.myapplication.storage.data

import com.kursor.myapplication.storage.domain.Message

interface MessageStorage {

    fun saveMessageToRegularSettings(message: Message)
    fun getMessageFromRegularSettings(): Message?

    fun saveMessageToEncryptedSettings(message: Message)
    fun getMessageFromEncryptedSettings(): Message?

}