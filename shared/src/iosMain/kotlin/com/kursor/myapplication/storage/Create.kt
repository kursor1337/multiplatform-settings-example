package com.kursor.myapplication.storage

import com.russhwolf.settings.KeychainSettings
import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import org.koin.core.scope.Scope
import platform.Foundation.NSUserDefaults

actual fun Scope.createSettings(name: String, encrypted: Boolean): Settings {
    return if (encrypted) createEncryptedSettings() else createRegularSettings()
}

private fun createRegularSettings(): Settings {
    return NSUserDefaultsSettings(delegate = NSUserDefaults.standardUserDefaults)
}

private fun createEncryptedSettings(): Settings {
    return KeychainSettings()
}