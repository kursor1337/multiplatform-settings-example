package com.kursor.myapplication.storage.ui

import com.arkivanov.decompose.ComponentContext
import com.kursor.myapplication.storage.data.MessageStorage
import com.kursor.myapplication.storage.domain.Message
import kotlinx.coroutines.flow.MutableStateFlow

class RealMessageComponent(
    componentContext: ComponentContext,
    private val messageStorage: MessageStorage
) : ComponentContext by componentContext, MessageComponent {

    override val messageState: MutableStateFlow<String> = MutableStateFlow("")

    override val textFromSettingsState: MutableStateFlow<String> = MutableStateFlow("")

    override fun onSaveToRegularClick() {
        println("onSaveToRegularClick: ${messageState.value}")
        messageStorage.saveMessageToRegularSettings(Message(messageState.value))
    }

    override fun onSaveToEncryptedClick() {
        println("onSaveToEncryptedClick: ${messageState.value} ")
        messageStorage.saveMessageToEncryptedSettings(Message(messageState.value))
    }

    override fun onGetFromRegularClick() {
        messageStorage.getMessageFromRegularSettings()?.let {
            textFromSettingsState.value = it.text
            println(":onGetFromRegularClick: $it")
        }
    }

    override fun onGetFromEncryptedClick() {
        messageStorage.getMessageFromEncryptedSettings()?.let {
            textFromSettingsState.value = it.text
            println(":onGetFromEncryptedClick: $it")
        }
    }

    override fun onMessageChanged(text: String) {
        messageState.value = text
    }


}