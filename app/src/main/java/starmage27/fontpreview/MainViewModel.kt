package starmage27.fontpreview

import android.app.Application
import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import starmage27.fontpreview.data.FontInfo
import starmage27.fontpreview.data.FontTable
import starmage27.fontpreview.data.TableDirectory
import starmage27.fontpreview.data.MutableFontInfo
import starmage27.fontpreview.data.getNameRecord
import starmage27.fontpreview.data.getNamingTable
import starmage27.fontpreview.data.readTableDirectory
import java.io.File
import java.io.RandomAccessFile
import java.nio.ByteBuffer

class MainViewModel(application: Application) : AndroidViewModel(application) {
    companion object {
        private const val TAG = "MainViewModel"
    }

    val settingsManager = SettingsManager(context = application)
    fun setSampleTextSetting(newText: String) {
        viewModelScope.launch {
            settingsManager.setSampleText(newText)
        }
    }

    val selectedFontFamily: StateFlow<FontFamily>
        field = MutableStateFlow<FontFamily>(FontFamily.Default)

    val selectedFontNameValues: StateFlow<FontInfo?>
        field = MutableStateFlow(null)


    fun loadFile(uri: Uri?, context: Context = getApplication()) {
        if (uri == null) return

        viewModelScope.launch {
            try {
                val fontFile = File(context.cacheDir, "fontfile.tmp")
                context.contentResolver.openInputStream(uri)?.use { inputStream ->
                    fontFile.outputStream().use { outputStream ->
                        inputStream.copyTo(outputStream)
                    }
                    fontFile
                }

                if (!fontFile.exists()) {
                    Log.e(TAG, "Could not load file")
                    return@launch
                }

                val font = Font(file = fontFile)
                selectedFontFamily.update {
                    FontFamily(font)
                }

                // Parsing //

                val tableDirectory: TableDirectory
                fontFile.inputStream().use { inputStream ->
                    tableDirectory = inputStream.readTableDirectory()
                    if (!tableDirectory.isValid()) {
                        Log.e(TAG, "tableDirectoryIsInvalid")
                        return@launch
                    }
                }

                val nameRecord = tableDirectory.tableRecords.find { it.isTable(FontTable.NamingTable) } ?: return@launch

                // Naming Table parsing //

                val namingTableBytes = ByteArray(nameRecord.length)
                RandomAccessFile(fontFile, "r").use {
                    it.seek(nameRecord.offset)
                    it.readFully(namingTableBytes)
                }
                val namingTableBuffer = ByteBuffer.wrap(namingTableBytes)
                val namingTable = namingTableBuffer.getNamingTable()

                repeat(namingTable.count) {
                    val nameRecord = namingTableBuffer.getNameRecord()
                    namingTable.nameRecords.add(nameRecord)
                }

                val fontInfo = MutableFontInfo()
                namingTable.nameRecords.forEach { nameRecord ->
                    val byteArray = ByteArray(nameRecord.length)
                    namingTableBuffer.position(nameRecord.offset + 6 + (12 * namingTable.nameRecords.size))
                    namingTableBuffer.get(byteArray)

                    val charset = if (nameRecord.platformID == 1) {
                        Charsets.ISO_8859_1
                    } else {
                        Charsets.UTF_16BE
                    }

                    val info = String(byteArray, charset)

                    fontInfo.setInfo(nameRecord.nameID, info)
                }
                selectedFontNameValues.update { fontInfo.getImmutable() }
                Log.w(TAG, fontInfo.toString())

            } catch (e: Exception) {
                Log.e(TAG, e.localizedMessage, e)
            }
        }
    }
}