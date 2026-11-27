package starmage27.apputils.compose

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers

const val uiMode = Configuration.UI_MODE_NIGHT_YES

@Preview(
    name = "Phone vertical", group = "phone",
    heightDp = 810, widthDp = 384,
    uiMode = uiMode,
    wallpaper = Wallpapers.BLUE_DOMINATED_EXAMPLE,
)
annotation class MyVerticalPhonePreview
@Preview(
    name = "Phone horizontal", group = "phone",
    heightDp = 384, widthDp = 810,
    uiMode = uiMode,
    wallpaper = Wallpapers.YELLOW_DOMINATED_EXAMPLE
)
annotation class MyHorizontalPhonePreview

@Preview(
    name = "Tablet vertical", group = "tablet",
    heightDp = 1205, widthDp = 753,
    uiMode = uiMode,
    wallpaper = Wallpapers.GREEN_DOMINATED_EXAMPLE,
)
annotation class MyVerticalTabletPreview
@Preview(
    name = "Tablet horizontal", group = "tablet",
    uiMode = uiMode,
    heightDp = 753, widthDp = 1205,
    wallpaper = Wallpapers.RED_DOMINATED_EXAMPLE
)
annotation class MyHorizontalTabletPreview

@Preview(
    name = "Small", group = "small",
    heightDp = 384, widthDp = 384,
    uiMode = uiMode,
    wallpaper = Wallpapers.NONE
)
annotation class MySmallPreview

@MyVerticalPhonePreview
@MyHorizontalPhonePreview
annotation class MyPhonePreviews

@MyVerticalTabletPreview
@MyHorizontalTabletPreview
annotation class MyTabletPreviews

@MyVerticalPhonePreview
@MyVerticalTabletPreview
@MySmallPreview
annotation class MyVerticalPreviews

@MyHorizontalPhonePreview
@MyHorizontalTabletPreview
annotation class MyHorizontalPreviews

@MyPhonePreviews
@MyTabletPreviews
@MySmallPreview
annotation class MyPreviews
