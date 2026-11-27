package starmage27.apputils.compose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.union
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.foundation.layout.WindowInsets as ComposeWindowInsets

@Composable
fun getPaddingsForInsets(
    density: Density = LocalDensity.current,
    insets: ComposeWindowInsets = ComposeWindowInsets.systemBars.union(ComposeWindowInsets.displayCutout),
    layoutDirection: LayoutDirection = LayoutDirection.Ltr
): PaddingValues {
    val paddings = with (density) {
        PaddingValues(
            start = insets.getLeft(density, layoutDirection).toDp(),
            end = insets.getRight(density, layoutDirection).toDp(),
            top = insets.getTop(density).toDp(),
            bottom = insets.getBottom(density).toDp(),
        )
    }
    return paddings
}