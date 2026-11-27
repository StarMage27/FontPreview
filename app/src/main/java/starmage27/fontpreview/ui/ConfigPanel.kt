package starmage27.fontpreview.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import starmage27.apputils.compose.getPaddingsForInsets
import starmage27.apputils.connected.ConnectedRoundedCornerShape
import starmage27.apputils.connected.ConnectionSide
import starmage27.fontpreview.Panagrams
import starmage27.fontpreview.R
import starmage27.fontpreview.data.FontInfo
import starmage27.fontpreview.ui.theme.FontPreviewTheme

@Composable
fun ConfigPanel(
    modifier: Modifier = Modifier,
    textFieldState: TextFieldState = TextFieldState(Panagrams.qbf),
    fontInfo: FontInfo? = null,
    onShowDialog: () -> Unit = {},
    onLoadFile: () -> Unit = {},
) {
    val insetPaddings = getPaddingsForInsets()

    val shape = ConnectedRoundedCornerShape(
        freeCornerRadius = 20.dp,
        connectedCornerRadius = 8.dp,
        connectionSide = ConnectionSide.ALL
    )
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)
    ) {
        Column(modifier = modifier
            .padding(8.dp)
            .padding(bottom = insetPaddings.calculateBottomPadding())
            .imePadding()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Max)
                ,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    modifier = Modifier.fillMaxHeight(),
                    onClick = onLoadFile,
                    shape = shape.copy(connectionSide = ConnectionSide.BOTTOM_AND_END)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_file_open_24),
                        contentDescription = stringResource(R.string.load_font),
                    )
                }
                Spacer(Modifier.size(4.dp))
                Surface(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                    ,
                    shape = shape.copy()
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        val text = fontInfo?.fontFamilyName ?: stringResource(R.string.font_preview)
                        Text(
                            modifier = Modifier
                                .padding(4.dp)
                            ,
                            text = text,
                            textAlign = TextAlign.Center
                        )
                    }
                }
                Spacer(Modifier.size(4.dp))
                Button(
                    modifier = Modifier.fillMaxHeight(),
                    onClick = onShowDialog,
                    enabled = fontInfo != null,
                    shape = shape.copy(connectionSide = ConnectionSide.BOTTOM_AND_START)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_info_24),
                        contentDescription = stringResource(R.string.information),
                    )
                }
            }
            Spacer(Modifier.size(4.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Max)
                ,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val focusRequester = remember { FocusRequester() }
                val focusManager = LocalFocusManager.current
                val menuExpanded = remember { mutableStateOf(false) }

                OutlinedTextField(
                    modifier = Modifier.weight(1f).focusRequester(focusRequester),
                    state = textFieldState,
                    textStyle = MaterialTheme.typography.bodyLarge,
                    lineLimits = TextFieldLineLimits.SingleLine,
                    shape = shape.copy(connectionSide = ConnectionSide.TOP_AND_END),
                    enabled = !menuExpanded.value,
                    colors = OutlinedTextFieldDefaults.colors().copy(
                        unfocusedContainerColor = MaterialTheme.colorScheme.background,
                        focusedContainerColor = MaterialTheme.colorScheme.background,
                        disabledContainerColor = MaterialTheme.colorScheme.background,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.outlineVariant,
                        disabledIndicatorColor = MaterialTheme.colorScheme.background,
                    )
                )
                Spacer(Modifier.width(4.dp))
                Box {
                    Button(
                        modifier = Modifier.fillMaxHeight(),
                        shape = shape.copy(connectionSide = ConnectionSide.TOP_AND_START),
                        onClick = {
                            focusManager.clearFocus()
                            menuExpanded.value = true
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_text_snippet_24),
                            contentDescription = "Select a panagram",
                        )
                    }
                    DropdownMenu(
                        expanded = menuExpanded.value,
                        onDismissRequest = { menuExpanded.value = false }
                    ) {
                        for (panagram in Panagrams.panagrams) {
                            DropdownMenuItem(
                                text = { Text(panagram) },
                                onClick = {
                                    textFieldState.setTextAndPlaceCursorAtEnd(panagram)
                                    focusManager.clearFocus()
                                    menuExpanded.value = false
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ConfigPanelPreview() {
    FontPreviewTheme {
        ConfigPanel()
    }
}