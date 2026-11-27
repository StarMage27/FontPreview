package starmage27.fontpreview

import android.util.Log
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.drop
import starmage27.fontpreview.ui.HomeScreen
import starmage27.fontpreview.ui.theme.FontPreviewTheme
import kotlin.time.Duration.Companion.milliseconds

@OptIn(FlowPreview::class)
@Composable
fun RootScreen(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel,
) {
    val fontFamily = mainViewModel.selectedFontFamily.collectAsStateWithLifecycle()
    val nameValues = mainViewModel.selectedFontNameValues.collectAsStateWithLifecycle()
    val sampleText by mainViewModel.settingsManager.sampleText.collectAsStateWithLifecycle("")

    val textFieldState = rememberTextFieldState("")
    LaunchedEffect(sampleText) {
        if (sampleText.isNotBlank() && textFieldState.text.toString() != sampleText) {
            textFieldState.setTextAndPlaceCursorAtEnd(sampleText)
            Log.d("RootScreen", "Loaded text: $sampleText")
        }
    }
    LaunchedEffect(textFieldState) {
        snapshotFlow { textFieldState.text.toString() }
            .drop(1)
            .debounce(500.milliseconds)
            .distinctUntilChanged()
            .collect { textToSave ->
                if (textToSave != sampleText) {
                    mainViewModel.setSampleTextSetting(textToSave)
                    Log.d("RootScreen", "Saved text: $textToSave")
                }
            }
    }

    FontPreviewTheme {
        HomeScreen(
            modifier = modifier,
            fontFamily = fontFamily.value,
            fontInfo = nameValues.value,
            textFieldState = textFieldState,
            onLoadFile = { uri -> mainViewModel.loadFile(uri) }
        )
    }
}