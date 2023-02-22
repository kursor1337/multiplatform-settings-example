package com.kursor.myapplication.storage.ui

import kotlinx.coroutines.flow.StateFlow

interface MessageComponent {

    val messageState: StateFlow<String>
    val textFromSettingsState: StateFlow<String>

    fun onSaveToRegularClick()
    fun onSaveToEncryptedClick()

    fun onGetFromRegularClick()
    fun onGetFromEncryptedClick()

    fun onMessageChanged(text: String)

}