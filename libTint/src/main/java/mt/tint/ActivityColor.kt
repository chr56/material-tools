/**
 * @author Karim Abou Zeid (kabouzeid)
 */
@file:JvmName("ActivityColor")

package mt.tint

import android.app.Activity
import android.app.ActivityManager.TaskDescription
import android.os.Build
import android.view.View
import android.view.WindowInsetsController
import androidx.annotation.ColorInt
import androidx.appcompat.widget.Toolbar
import mt.pref.ThemeColor
import mt.tint.viewtint.setMenuColor_White
import mt.tint.viewtint.setToolbarColorAuto
import mt.util.color.isColorLight
import mt.util.color.stripAlpha

fun Activity.setStatusbarColorAuto() =
    setStatusbarColor(
        ThemeColor.statusBarColor(this)
    )

/**
 * @param color statusbar background color
 * @param stubViewId stub view of statusbar if you have any
 */
fun Activity.setStatusbarColor(@ColorInt color: Int, stubViewId: Int = 0) {
    with(this.window) {
        statusBarColor = color
        if (stubViewId != 0) {
            decorView.rootView.findViewById<View>(stubViewId)
                ?.setBackgroundColor(color)
        }
    }
    requireLightStatusbarAuto(color)
}

fun Activity.requireLightStatusbarAuto(bgColor: Int) =
    requireLightStatusbar(isColorLight(bgColor))

fun Activity.requireLightStatusbar(enabled: Boolean) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val wic = this.window.insetsController
        if (wic != null) {
            if (enabled) {
                wic.setSystemBarsAppearance(
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                )
            } else {
                wic.setSystemBarsAppearance(
                    0,
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                )
            }
        }
    } else {
        this.window.decorView.run {
            val old = systemUiVisibility
            systemUiVisibility =
                if (enabled) {
                    old or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                } else {
                    old and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
                }
        }
    }
}

fun Activity.setNavigationBarColorAuto() =
    setNavigationBarColor(
        ThemeColor.navigationBarColor(this)
    )

/**
 * @param color NavigationBar background color
 */
fun Activity.setNavigationBarColor(@ColorInt color: Int) {
    this.window.navigationBarColor = color
    requireLightNavigationBarAuto(color)
}

fun Activity.requireLightNavigationBarAuto(bgColor: Int) {
    requireLightNavigationBar(isColorLight(bgColor))
}

fun Activity.requireLightNavigationBar(enabled: Boolean) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        this.window.insetsController?.let { insetsController ->
            if (enabled) {
                insetsController.setSystemBarsAppearance(
                    WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS,
                    WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS
                )
            } else {
                insetsController.setSystemBarsAppearance(
                    0,
                    WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS
                )
            }
        }
    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        this.window.decorView.run {
            val visibility = if (enabled) {
                systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
            } else {
                systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR.inv()
            }
            systemUiVisibility = visibility
        }
    }
}

fun Activity.setActivityToolbarColorAuto(toolbar: Toolbar) =
    setActivityToolbarColor(
        toolbar,
        ThemeColor.primaryColor(this)
    )

fun Activity.setActivityToolbarColor(toolbar: Toolbar, color: Int) {
    toolbar.setBackgroundColor(color)
    setMenuColor_White(this, toolbar, toolbar.menu)
    setToolbarColorAuto(this, toolbar, null, color)
}

fun Activity.setTaskDescriptionColorAuto() =
    setTaskDescriptionColor(ThemeColor.primaryColor(this))

fun Activity.setTaskDescriptionColor(@ColorInt color: Int) {
    // Task description requires fully opaque color
    val c = stripAlpha(color)
    // Sets color of entry in the system recents page
    setTaskDescription(
        if (Build.VERSION.SDK_INT >= 33) {
            TaskDescription.Builder()
                .setBackgroundColor(c).setLabel(this.title as String)
                .build()
        } else {
            TaskDescription(this.title as String, null, c)
        }
    )
}
