package starmage27.apputils.compose

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun RotatingIconButton(
    modifier: Modifier = Modifier,
    rotated: Boolean = true,
    enabled: Boolean = true,
    icon: ImageVector,
    tint: Color = LocalContentColor.current,
    angle: Float = 0f,
    rotatedAngle: Float = 180f,
    contentDescription: String?,
    rotatedContentDescription: String? = null,
    colors: IconButtonColors = IconButtonDefaults.iconButtonColors(),
    interactionSource: MutableInteractionSource? = null,
    shape: Shape = IconButtonDefaults.standardShape,
    onClick: () -> Unit = {},
) {
    val rotation by animateFloatAsState(
        targetValue = if (rotated) rotatedAngle else angle
    )

    val description = if (rotated && rotatedContentDescription != null) {
        rotatedContentDescription
    } else {
        contentDescription
    }

    IconButton(
        modifier = modifier,
        enabled = enabled,
        colors = colors,
        interactionSource = interactionSource,
        shape = shape,
        onClick = onClick
    ) {
        Icon(
            modifier = Modifier
                .fillMaxSize()
                .rotate(rotation)
            ,
            imageVector = icon,
            contentDescription = description,
            tint = tint
        )
    }
}