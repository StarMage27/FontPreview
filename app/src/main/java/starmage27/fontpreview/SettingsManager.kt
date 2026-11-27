package starmage27.fontpreview

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("settings")

private val Keys = object {
    val SELECTED_MODE = intPreferencesKey("selected_mode")
    val SAMPLE_TEXT = stringPreferencesKey("sample_text")
}

class SettingsManager(context: Context) {
    private val dataStore = context.dataStore

    val selectedMode: Flow<Int> = dataStore.data
        .map { preferences ->
            preferences[Keys.SELECTED_MODE] ?: 0
        }

    val sampleText: Flow<String> = dataStore.data
        .map { preferences ->
            preferences[Keys.SAMPLE_TEXT] ?: Panagrams.qbf
        }

    suspend fun setSelectedMode(value: Int) {
        dataStore.edit { preferences ->
            preferences[Keys.SELECTED_MODE] = value
        }
    }

    suspend fun setSampleText(value: String) {
        dataStore.edit { preferences ->
            preferences[Keys.SAMPLE_TEXT] = value
        }
    }
}