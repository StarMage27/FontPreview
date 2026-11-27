package starmage27.apputils.connected

enum class ConnectionSide(
    val topStartConnected   : Boolean = false,
    val topEndConnected     : Boolean = false,
    val bottomStartConnected: Boolean = false,
    val bottomEndConnected  : Boolean = false,
) {
    NONE,
    TOP(topStartConnected = true, topEndConnected = true),
    BOTTOM(bottomStartConnected = true, bottomEndConnected = true),
    START(topStartConnected = true, bottomStartConnected = true),
    END(topEndConnected = true, bottomEndConnected = true),
    TOP_AND_START(topStartConnected = true, topEndConnected = true, bottomStartConnected = true),
    BOTTOM_AND_START(bottomStartConnected = true, bottomEndConnected = true, topStartConnected = true),
    TOP_AND_END(topStartConnected = true, topEndConnected = true, bottomEndConnected = true),
    BOTTOM_AND_END(bottomStartConnected = true, bottomEndConnected = true, topEndConnected = true),
    ALL(topStartConnected = true, topEndConnected = true, bottomStartConnected = true, bottomEndConnected = true);

    val anyStartConnected: Boolean
        get() = topStartConnected || bottomStartConnected
    val anyEndConnected: Boolean
        get() = topEndConnected || bottomEndConnected
    val anyTopConnected: Boolean
        get() = topStartConnected || topEndConnected
    val anyBottomConnected: Boolean
        get() = bottomStartConnected || bottomEndConnected

    val anyStartFree: Boolean
        get() = !topStartConnected || !bottomStartConnected
    val anyEndFree: Boolean
        get() = !topEndConnected || !bottomEndConnected
    val anyTopFree: Boolean
        get() = !topStartConnected || !topEndConnected
    val anyBottomFree: Boolean
        get() = !bottomStartConnected || !bottomEndConnected
}