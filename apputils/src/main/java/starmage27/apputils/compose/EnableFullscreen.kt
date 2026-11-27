package starmage27.apputils.compose

//import android.os.Build
//import android.view.View
//import android.view.WindowInsets
//import android.view.WindowInsetsController
//import androidx.activity.ComponentActivity
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.DisposableEffect
//import androidx.compose.ui.platform.LocalView
//
//@Composable
//fun EnableFullscreen() {
//    val view = LocalView.current
//    val window = (view.context as ComponentActivity).window
//    DisposableEffect(Unit) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//            window.insetsController?.let { controller ->
//                controller.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
//                controller.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
//            }
//        } else {
//            @Suppress("DEPRECATION")
//            window.decorView.systemUiVisibility = (
//                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//                            or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                            or View.SYSTEM_UI_FLAG_FULLSCREEN
//                    )
//        }
//
//        onDispose {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//                window.insetsController?.show(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
//            } else {
//                @Suppress("DEPRECATION")
//                window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_VISIBLE or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
//            }
//        }
//    }
//}