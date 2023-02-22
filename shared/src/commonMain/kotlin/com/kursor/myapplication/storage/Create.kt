package com.kursor.myapplication.storage

import com.russhwolf.settings.Settings
import org.koin.core.scope.Scope

expect fun Scope.createSettings(name: String, encrypted: Boolean = false): Settings