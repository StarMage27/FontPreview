package starmage27.apputils.connected

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
@NonRestartableComposable
fun ConnectedSurface(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    connectionSide: ConnectionSide = ConnectionSide.NONE,
    freeCornerRadius: Dp = 0.dp,
    connectedCornerRadius: Dp = 0.dp,
    color: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = contentColorFor(color),
    tonalElevation: Dp = 0.dp,
    shadowElevation: Dp = 0.dp,
    border: BorderStroke? = null,
    interactionSource: MutableInteractionSource? = null,
    onClick: () -> Unit = {},
    content: @Composable (() -> Unit)
) {
    val shape = remember { RoundedCornerShape(
        topStart = if (connectionSide.topStartConnected) connectedCornerRadius else freeCornerRadius,
        topEnd = if (connectionSide.topEndConnected) connectedCornerRadius else freeCornerRadius,
        bottomStart = if (connectionSide.bottomStartConnected) connectedCornerRadius else freeCornerRadius,
        bottomEnd = if (connectionSide.bottomEndConnected) connectedCornerRadius else freeCornerRadius,
    ) }

    Surface(
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        color = color,
        contentColor = contentColor,
        tonalElevation = tonalElevation,
        shadowElevation = shadowElevation,
        border = border,
        interactionSource = interactionSource,
        onClick = onClick,
        content = content
    )
}

@Composable
fun AnimatedConnectedSurface(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    connectionSide: ConnectionSide = ConnectionSide.NONE,
    freeCornerRadius: Dp = 0.dp,
    connectedCornerRadius: Dp = 0.dp,
    color: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = contentColorFor(color),
    tonalElevation: Dp = 0.dp,
    shadowElevation: Dp = 0.dp,
    border: BorderStroke? = null,
    interactionSource: MutableInteractionSource? = null,
    onClick: () -> Unit = {},
    content: @Composable (() -> Unit)
) {
    val topStartAnimated by animateDpAsState(
        targetValue = if (connectionSide.topStartConnected) connectedCornerRadius else freeCornerRadius,
        animationSpec = spring(),
    )
    val topEndAnimated by animateDpAsState(
        targetValue = if (connectionSide.topEndConnected) connectedCornerRadius else freeCornerRadius,
        animationSpec = spring(),
    )
    val bottomStartAnimated by animateDpAsState(
        targetValue = if (connectionSide.bottomStartConnected) connectedCornerRadius else freeCornerRadius,
        animationSpec = spring(),
    )
    val bottomEndAnimated by animateDpAsState(
        targetValue = if (connectionSide.bottomEndConnected) connectedCornerRadius else freeCornerRadius,
        animationSpec = spring(),
    )

    val shape = remember { RoundedCornerShape(
        topStart = topStartAnimated,
        topEnd = topEndAnimated,
        bottomStart = bottomStartAnimated,
        bottomEnd = bottomEndAnimated,
    ) }

    Surface(
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        color = color,
        contentColor = contentColor,
        tonalElevation = tonalElevation,
        shadowElevation = shadowElevation,
        border = border,
        interactionSource = interactionSource,
        onClick = onClick,
        content = content
    )
}
