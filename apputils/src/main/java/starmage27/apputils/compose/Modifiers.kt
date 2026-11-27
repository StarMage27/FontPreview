package starmage27.apputils.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview

fun Modifier.showWhenFits(
    onFitsUpdate: (Boolean) -> Unit = {}
) = layout { measurable, constraints ->
        // Check if measurable fits into constraints
        val fitsHeight = measurable.maxIntrinsicHeight(constraints.maxWidth) <= constraints.maxHeight
        val fitsWidth = measurable.maxIntrinsicWidth(constraints.maxHeight) <= constraints.maxWidth

        if (fitsHeight && fitsWidth) {
            onFitsUpdate(true)
            val placeable = measurable.measure(constraints)
            layout(placeable.width, placeable.height) {
                placeable.placeRelative(0, 0)
            }
        } else {
            onFitsUpdate(false)
            layout(0, 0) {}
        }
    }

fun Modifier.indev() = this.then(
    Modifier
        .graphicsLayer {
            compositingStrategy = CompositingStrategy.Offscreen
        }
        .drawWithContent {
        val col1 = Color.Black
        val col2 = Color.Yellow
        val colorStops = arrayOf(
            0.0f to col1,
            0.5f to col1,
            0.5f to col2,
            1f to col2,
        )
        drawContent()
        drawRect(
            Brush.linearGradient(
                colorStops = colorStops,
                end = Offset(64f, 64f),
                tileMode = TileMode.Repeated,
            ),
            alpha = 0.5f,
            blendMode = BlendMode.SrcAtop
        )
    }
)

@Preview
@Composable
fun PreviewIndev() {
    Column {
        Button(
            modifier = Modifier,
            onClick = {},
        ) {
            Text("Test")
        }
        Button(
            modifier = Modifier.indev(),
            onClick = {},
        ) {
            Text("Test")
        }
    }
}