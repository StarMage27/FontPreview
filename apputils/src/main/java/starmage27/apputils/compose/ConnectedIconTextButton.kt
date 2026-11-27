package starmage27.apputils.compose

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import starmage27.apputils.connected.ConnectedRoundedCornerShape
import starmage27.apputils.connected.ConnectionSide

@Composable
fun ConnectedIconTextButton(
    modifier: Modifier = Modifier,
    connectionSide: ConnectionSide = ConnectionSide.NONE,
    freeCornerRadius: Dp = 0.dp,
    connectedCornerRadius: Dp = 0.dp,
    padding: Dp = 0.dp,
    color: Color = MaterialTheme.colorScheme.secondaryContainer,
    contentColor: Color = contentColorFor(color),
    text: String,
    fontSize: TextUnit = TextUnit.Unspecified,
    icon: ImageVector?,
    iconSize: DpSize = DpSize(icon?.defaultWidth ?: 32.dp, icon?.defaultHeight ?: 32.dp),
    iconColor: Color = contentColor,
    contentDescription: String = text,
    tonalElevation: Dp = 0.dp,
    shadowElevation: Dp = 0.dp,
    onClick: () -> Unit = {},
) {
    val textFits = remember { mutableStateOf(true) }
    
    val shape = ConnectedRoundedCornerShape(
        connectionSide = connectionSide,
        freeCornerRadius = freeCornerRadius,
        connectedCornerRadius = connectedCornerRadius
    )

    Surface(
        modifier = modifier,
        onClick = onClick,
        shape = shape,
        color = color,
        contentColor = contentColor,
        tonalElevation = tonalElevation,
        shadowElevation = shadowElevation,
    ) {
        val horizontalPadding = if (textFits.value && connectionSide.anyStartFree)
            padding * 0.5f + freeCornerRadius - connectedCornerRadius - padding
        else
            padding * 0.5f

        Row(
            modifier = Modifier
                .padding(vertical = padding * 0.5f, horizontal = horizontalPadding)
            ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            val iconModifier = Modifier
                .padding(padding * 0.5f)
                .size(iconSize)
                .sizeIn(
                    minWidth = 0.dp          , minHeight = 0.dp,
                    maxWidth = iconSize.width, maxHeight = iconSize.height
                )
                .aspectRatio(1f)

            if (icon == null) {
                Box(
                    modifier = iconModifier.background(iconColor)
                ) { }
            } else {
                Icon(
                    imageVector = icon,
                    contentDescription = contentDescription,
                    tint = iconColor,
                    modifier = iconModifier
                )
            }
            Text(
                text = text,
                color = contentColor,
                fontSize = fontSize,
                softWrap = false,
                modifier = Modifier
                    .showWhenFits { fits -> textFits.value = fits }
                    .padding(padding * 0.5f)
                    .fillMaxWidth()
//                    .weight(1f, fill = textFits.value)
            )
        }
    }
}

@Preview(widthDp = 80, heightDp = 64)
@Preview(widthDp = 320, heightDp = 64, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ConnectedIconTextButtonPreview() {
    ConnectedIconTextButton(
        text = "Test Button",
        icon = null,
        padding = 12.dp,
        fontSize = 24.sp,
        iconSize = DpSize(64.dp, 64.dp),
        color = MaterialTheme.colorScheme.primary,
        freeCornerRadius = 32.dp,
        connectedCornerRadius = 8.dp,
        connectionSide = ConnectionSide.BOTTOM
    )
}