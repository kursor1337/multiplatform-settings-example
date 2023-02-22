package com.kursor.myapplication.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.defaultComponentContext
import com.kursor.myapplication.android.storage.MessageUi
import com.kursor.myapplication.storage.ui.RealMessageComponent
import org.koin.core.Koin
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class MainActivity : ComponentActivity(), KoinComponent {

    override fun getKoin(): Koin {
        return (application as App).koin
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val messageComponent = RealMessageComponent(defaultComponentContext(), get())
        setContent {
            MyApplicationTheme {
                MessageUi(component = messageComponent)
            }
        }
    }
}
