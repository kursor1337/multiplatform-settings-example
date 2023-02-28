package com.kursor.myapplication.storage.ui

import com.arkivanov.decompose.ComponentContext
import com.kursor.myapplication.storage.CMutableStateFlow
import com.kursor.myapplication.storage.data.MessageStorage
import com.kursor.myapplication.storage.domain.Message
import kotlinx.coroutines.flow.MutableStateFlow

class RealMessageComponent(
    componentContext: ComponentContext,
    private val messageStorage: MessageStorage
) : ComponentContext by componentContext, MessageComponent {

    override val messageState: CMutableStateFlow<String> = CMutableStateFlow("")

    override val textFromSettingsState: CMutableStateFlow<String> = CMutableStateFlow("")

    override fun onSaveToRegularClick() {
        println(":onSaveToRegularClick")
        messageStorage.saveMessageToRegularSettings(Message(messageState.value))
    }

    override fun onSaveToEncryptedClick() {
        println(":onSaveToEncryptedClick")
        messageStorage.saveMessageToEncryptedSettings(Message(messageState.value))
    }

    override fun onGetFromRegularClick() {
        messageStorage.getMessageFromRegularSettings()?.let {
            textFromSettingsState.value = it.text
            println(":onGetFromRegularClick ${it.text}")
        }
    }

    override fun onGetFromEncryptedClick() {
        messageStorage.getMessageFromEncryptedSettings()?.let {
            textFromSettingsState.value = it.text
            println(":onGetFromEncryptedClick ${it.text}")
        }
    }

    override fun onMessageChanged(text: String) {
        messageState.value = text
    }


}