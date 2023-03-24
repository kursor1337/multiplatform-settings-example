package com.kursor.myapplication.android.storage

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.kursor.myapplication.android.MyApplicationTheme
import com.kursor.myapplication.storage.CMutableStateFlow
import com.kursor.myapplication.storage.CStateFlow
import com.kursor.myapplication.storage.ui.MessageComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun MessageUi(
    component: MessageComponent,
    modifier: Modifier = Modifier
) {
    val text by component.textFromSettingsState.collectAsState()
    val message by component.messageState.collectAsState()
    Scaffold(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = text,
                style = TextStyle(
                    fontSize = 30.sp
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = message,
                onValueChange = component::onMessageChanged,
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = component::onSaveToRegularClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Save to regular")
            }
            Button(
                onClick = component::onSaveToEncryptedClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Save to encrypted")
            }
            Button(
                onClick = component::onGetFromRegularClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Get from regular")
            }
            Button(
                onClick = component::onGetFromEncryptedClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Get from encrypted")
            }
        }
    }

}

@Preview
@Composable
fun MessageUiPreview() {
    MyApplicationTheme {
        MessageUi(
            component = FakeMessageComponent(),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

class FakeMessageComponent : MessageComponent {

    override val messageState: CStateFlow<String> = CMutableStateFlow("duck")
    override val textFromSettingsState: CStateFlow<String> = CMutableStateFlow("duck")

    override fun onSaveToRegularClick() = Unit
    override fun onSaveToEncryptedClick() = Unit
    override fun onGetFromRegularClick() = Unit
    override fun onGetFromEncryptedClick() = Unit

    override fun onMessageChanged(text: String) = Unit
}


