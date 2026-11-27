package starmage27.fontpreview.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import starmage27.apputils.connected.ConnectedRoundedCornerShape
import starmage27.apputils.connected.ConnectionSide
import starmage27.fontpreview.R
import starmage27.fontpreview.data.FontInfo
import starmage27.fontpreview.ui.theme.FontPreviewTheme

@Composable
fun FontInfoDialog(
    modifier: Modifier = Modifier,
    fontInfo: FontInfo,
    onDismiss: () -> Unit = {},
    show: Boolean = true,
) {
    if (show) {
        Dialog(onDismissRequest = onDismiss) {
            FontInfoDialogUI(
                modifier = modifier.padding(vertical = 32.dp),
                fontInfo = fontInfo,
                onDismiss = onDismiss,
            )
        }
    }
}


@Composable
fun FontInfoDialogUI(
    modifier: Modifier = Modifier,
    fontInfo: FontInfo = FontInfo.exampleFontInfo,
    onDismiss: () -> Unit = {},
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(28.dp),
        tonalElevation = 8.dp,
        shadowElevation = 8.dp,
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
            ,
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            val shape = ConnectedRoundedCornerShape(
                freeCornerRadius = 20.dp,
                connectedCornerRadius = 8.dp,
                connectionSide = ConnectionSide.ALL
            )

            Surface(
                modifier = Modifier
                    .height(intrinsicSize = IntrinsicSize.Min)
                ,
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = shape.copy(connectionSide = ConnectionSide.BOTTOM)
            ) {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth()
                        ,
                        text = stringResource(R.string.information),
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center
                    )
                }
            }
            Spacer(Modifier.size(4.dp))

            FontInfoItems(
                modifier = Modifier.weight(1f, fill = false),
                fontNameValues = fontInfo,
                shape = shape,
            )

            Spacer(Modifier.size(4.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(intrinsicSize = IntrinsicSize.Min)
                ,
                onClick = onDismiss,
                shape = shape.copy(connectionSide = ConnectionSide.TOP)
            ) {
                Text(
                    text = stringResource(R.string.close)
                )
            }
        }
    }
}

@Composable
fun FontInfoItems(
    modifier: Modifier = Modifier,
    fontNameValues: FontInfo,
    shape: Shape = RoundedCornerShape(8.dp)
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .verticalScroll(scrollState)
        ,
    ) {
        val entriesModifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 4.dp)
        FontInfoEntry(
            modifier = entriesModifier,
            name = stringResource(R.string.font_family),
            value = fontNameValues.fontFamilyName,
            shape = shape,
        )
        FontInfoEntry(
            modifier = entriesModifier,
            name = stringResource(R.string.font_subfamily),
            value = fontNameValues.fontSubfamilyName,
            shape = shape,
        )
        FontInfoEntry(
            modifier = entriesModifier,
            name = stringResource(R.string.description),
            value = fontNameValues.description,
            shape = shape,
        )
        FontInfoEntry(
            modifier = entriesModifier,
            name = stringResource(R.string.version),
            value = fontNameValues.version,
            shape = shape,
        )
        FontInfoEntry(
            modifier = entriesModifier,
            name = stringResource(R.string.trademark),
            value = fontNameValues.trademark,
            shape = shape,
        )
        FontInfoEntry(
            modifier = entriesModifier,
            name = stringResource(R.string.manufacturer),
            value = fontNameValues.manufacturerName,
            shape = shape,
        )
        FontInfoEntry(
            modifier = entriesModifier,
            name = stringResource(R.string.designer),
            value = fontNameValues.designer,
            shape = shape,
        )
        FontInfoEntry(
            modifier = entriesModifier,
            name = stringResource(R.string.vendor_url),
            value = fontNameValues.vendorURL,
            shape = shape,
        )
        FontInfoEntry(
            modifier = entriesModifier,
            name = stringResource(R.string.designer_url),
            value = fontNameValues.designerURL,
            shape = shape,
        )
        FontInfoEntry(
            modifier = entriesModifier,
            name = stringResource(R.string.license_description),
            value = fontNameValues.licenseDescription,
            shape = shape,
        )
        FontInfoEntry(
            modifier = entriesModifier,
            name = stringResource(R.string.license_info_url),
            value = fontNameValues.licenseInfoURL,
            shape = shape,
        )
        FontInfoEntry(
            modifier = Modifier.fillMaxWidth(),
            name = stringResource(R.string.copyright),
            value = fontNameValues.copyrightNotice,
            shape = shape,
        )
    }
}

@Composable
fun FontInfoEntry(
    modifier: Modifier = Modifier,
    name: String = "",
    value: String = "",
    shape: Shape = RoundedCornerShape(20.dp),
) {
    if (value.isNotBlank()) {
        Surface(
            modifier,
            color = MaterialTheme.colorScheme.surfaceContainer,
            shape = shape
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                SelectionContainer {
                    Text(text = value.trim())
                }
            }
        }
    }
}

@Preview(showBackground = false)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = false)
@Composable
fun FontInfoDialogPreview() {
    FontPreviewTheme {
        FontInfoDialogUI()
    }
}