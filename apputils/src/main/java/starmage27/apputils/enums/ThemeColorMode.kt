package starmage27.apputils.enums

enum class ThemeColorMode {
    AUTO, DARK, LIGHT;

    val isAuto: Boolean
        get() = this == AUTO
    val isDark: Boolean
        get() = this == DARK
    val isLight: Boolean
        get() = this == LIGHT
}