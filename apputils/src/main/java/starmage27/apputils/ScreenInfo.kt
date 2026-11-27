package starmage27.apputils

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.platform.WindowInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import starmage27.apputils.enums.ScreenOrientation
import starmage27.apputils.enums.ScreenType

/**
 * [ScreenInfo] class unifies screen info and makes it easier to access
 */
class ScreenInfo(
    val lowBoundWidth  : Dp = 600.dp, // Bounds taken from compose adaptive
    val highBoundWidth : Dp = 840.dp, //
    val lowBoundHeight : Dp = 480.dp, //
    val highBoundHeight: Dp = 900.dp, //
) {
    var width: Dp = 0.dp
        private set
    var height: Dp = 0.dp
        private set
    var type: ScreenType = ScreenType.PHONE
        private set(value) {
            this.isPhone = value == ScreenType.PHONE
            this.isTablet = value == ScreenType.TABLET
            this.isSmall = value == ScreenType.SMALL
            field = value
        }
    var orientation: ScreenOrientation = ScreenOrientation.VERTICAL
        private set(value) {
            this.isVertical = value == ScreenOrientation.VERTICAL
            this.isHorizontal = value == ScreenOrientation.HORIZONTAL
            field = value
        }
    // A way to check type or orientation
    var isPhone: Boolean = type == ScreenType.PHONE
        private set
    var isTablet: Boolean = type == ScreenType.TABLET // Big if true
        private set
    var isSmall: Boolean = type == ScreenType.SMALL
        private set
    var isVertical: Boolean = orientation == ScreenOrientation.VERTICAL
        private set
    var isHorizontal: Boolean = orientation == ScreenOrientation.HORIZONTAL
        private set

    // This gets the current values
    @Composable
    fun compose(
        info: WindowInfo = LocalWindowInfo.current,
    ): ScreenInfo {
        val density = LocalDensity.current
        val configuration: Configuration = LocalConfiguration.current

        val width = with(density) { info.containerSize.width.toDp() }
        val height = with(density) { info.containerSize.height.toDp() }

        val type = if (width <= this.lowBoundWidth) { // Screen width is thin
            if (height <= this.lowBoundHeight) { // Screen height is short
                ScreenType.SMALL
            } else { // Screen height is medium or tall
                ScreenType.PHONE
            }
        } else { // Screen width is medium or tall
            if (height <= this.lowBoundHeight) { // Screen height is short
                ScreenType.PHONE
            } else { // Screen height is medium or tall
                ScreenType.TABLET
            }
        }

        val orientation = if (type == ScreenType.SMALL) {
            ScreenOrientation.VERTICAL
        } else when(configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> ScreenOrientation.HORIZONTAL
            Configuration.ORIENTATION_PORTRAIT -> ScreenOrientation.VERTICAL
            else -> ScreenOrientation.VERTICAL
        }

        this.width = width
        this.height = height
        this.orientation = orientation
        this.type = type

        return this
    }
}