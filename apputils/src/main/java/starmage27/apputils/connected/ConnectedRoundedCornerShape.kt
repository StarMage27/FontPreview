package starmage27.apputils.connected

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

data class ConnectedRoundedCornerShape(
    val connectionSide: ConnectionSide = ConnectionSide.NONE,
    val freeCornerRadius: Dp = 0.dp,
    val connectedCornerRadius: Dp = 0.dp,
): CornerBasedShape(
    topStart = if (connectionSide.topStartConnected) CornerSize(connectedCornerRadius) else CornerSize(
        freeCornerRadius
    ),
    topEnd = if (connectionSide.topEndConnected) CornerSize(connectedCornerRadius) else CornerSize(
        freeCornerRadius
    ),
    bottomStart = if (connectionSide.bottomStartConnected) CornerSize(connectedCornerRadius) else CornerSize(
        freeCornerRadius
    ),
    bottomEnd = if (connectionSide.bottomEndConnected) CornerSize(connectedCornerRadius) else CornerSize(
        freeCornerRadius
    ),
) {
    override fun createOutline(
        size: Size,
        topStart: Float,
        topEnd: Float,
        bottomEnd: Float,
        bottomStart: Float,
        layoutDirection: LayoutDirection,
    ) =
        if (topStart + topEnd + bottomEnd + bottomStart == 0.0f) {
            Outline.Rectangle(size.toRect())
        } else {
            Outline.Rounded(
                RoundRect(
                    rect = size.toRect(),
                    topLeft = CornerRadius(if (layoutDirection == LayoutDirection.Ltr) topStart else topEnd),
                    topRight = CornerRadius(if (layoutDirection == LayoutDirection.Ltr) topEnd else topStart),
                    bottomRight =
                        CornerRadius(if (layoutDirection == LayoutDirection.Ltr) bottomEnd else bottomStart),
                    bottomLeft =
                        CornerRadius(if (layoutDirection == LayoutDirection.Ltr) bottomStart else bottomEnd),
                )
            )
        }

    override fun copy(
        topStart: CornerSize,
        topEnd: CornerSize,
        bottomEnd: CornerSize,
        bottomStart: CornerSize,
    ) =
        RoundedCornerShape(
            topStart = topStart,
            topEnd = topEnd,
            bottomEnd = bottomEnd,
            bottomStart = bottomStart,
        )

    override fun toString(): String {
        return "ConnectedRoundedCornerShape(topStart = $topStart, topEnd = $topEnd, bottomEnd = " +
                "$bottomEnd, bottomStart = $bottomStart)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is RoundedCornerShape) return false

        if (topStart != other.topStart) return false
        if (topEnd != other.topEnd) return false
        if (bottomEnd != other.bottomEnd) return false
        if (bottomStart != other.bottomStart) return false

        return true
    }

    override fun hashCode(): Int {
        var result = topStart.hashCode()
        result = 31 * result + topEnd.hashCode()
        result = 31 * result + bottomEnd.hashCode()
        result = 31 * result + bottomStart.hashCode()
        return result
    }
}