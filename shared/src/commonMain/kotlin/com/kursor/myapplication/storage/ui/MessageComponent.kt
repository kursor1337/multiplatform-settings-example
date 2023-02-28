package com.kursor.myapplication.storage.ui

import com.kursor.myapplication.storage.CStateFlow

interface MessageComponent {

    val messageState: CStateFlow<String>
    val textFromSettingsState: CStateFlow<String>

    fun onSaveToRegularClick()
    fun onSaveToEncryptedClick()

    fun onGetFromRegularClick()
    fun onGetFromEncryptedClick()

    fun onMessageChanged(text: String)

}