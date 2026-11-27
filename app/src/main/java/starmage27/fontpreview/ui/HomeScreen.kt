package starmage27.fontpreview.ui

import android.content.res.Configuration
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import starmage27.apputils.compose.getPaddingsForInsets
import starmage27.fontpreview.Panagrams
import starmage27.fontpreview.data.FontInfo
import starmage27.fontpreview.ui.theme.FontPreviewTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    fontFamily: FontFamily? = FontFamily.Default,
    fontInfo: FontInfo? = null,
    textFieldState: TextFieldState = TextFieldState(Panagrams.qbf),
    onLoadFile: (uri: Uri?) -> Unit = { _ -> }
) {
    val density = LocalDensity.current
    val insetPaddings = getPaddingsForInsets()
    val fileLoader = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri -> onLoadFile(uri) }
    )
    val showFontInfoDialog = remember { mutableStateOf(false) }
    val extraSpacing = remember { mutableFloatStateOf(0f) }

    Box(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background))

    Column(modifier.padding(insetPaddings)) {
        val fontSizes = remember { Array(10) { i -> (8 + (i * i) * 2).sp } }
        Column(
            modifier = Modifier
                .horizontalScroll(state = rememberScrollState())
                .verticalScroll(state = rememberScrollState())
                .fillMaxHeight()
            ,
        ) {
            Spacer(Modifier.size(4.dp))
            for (fontSize in fontSizes) {
                Text(
                    modifier = Modifier.padding(start = 4.dp),
                    text = textFieldState.text.toString(),
                    fontFamily = fontFamily,
                    fontSize = fontSize,
                    lineHeight = fontSize,
                    softWrap = false,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
            Spacer(Modifier.size(extraSpacing.floatValue.dp))
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.weight(1f))
        ConfigPanel(
            modifier = Modifier
                .widthIn(0.dp, 500.dp)
                .onSizeChanged { newSize ->
                    with(density) {
                        extraSpacing.floatValue = newSize.height.toDp().value
                    }
                }
            ,
            textFieldState = textFieldState,
            fontInfo = fontInfo,
            onShowDialog = { showFontInfoDialog.value = true },
            onLoadFile = { fileLoader.launch("font/*") }
        )
    }
    
    if (fontInfo != null) {
        FontInfoDialog(
            fontInfo = fontInfo,
            show = showFontInfoDialog.value,
            onDismiss = { showFontInfoDialog.value = false }
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenPreview() {
    FontPreviewTheme {
        HomeScreen()
    }
}