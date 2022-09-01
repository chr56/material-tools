@file:JvmName("FullScreenUtil")

package mt.util.windows

import android.app.Activity
import android.view.View

fun Activity.setFullScreenAndIncludeStatusBar() {
    @Suppress("DEPRECATION")
    window.decorView.systemUiVisibility =
        (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
}

fun Activity.restoreNotFullsScreen() {
    @Suppress("DEPRECATION")
    window.decorView.systemUiVisibility -=
        (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
}
