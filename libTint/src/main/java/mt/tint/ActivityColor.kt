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
    setStatusbarColor(ThemeColor.statusBarColor(this))

fun Activity.setStatusbarColor(color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        this.window.statusBarColor = color
        setLightStatusbarAuto(color)
    }
}

fun Activity.setLightStatusbarAuto(bgColor: Int) =
    setLightStatusbar(isColorLight(bgColor))

fun Activity.setLightStatusbar(enabled: Boolean) {
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
    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val decorView = this.window.decorView.run {
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

fun Activity.setLightNavigationbar(enabled: Boolean) {
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

fun Activity.setLightNavigationbarAuto(bgColor: Int) =
    setLightNavigationbar(isColorLight(bgColor))

fun Activity.setNavigationbarColorAuto() =
    setNavigationbarColor(ThemeColor.navigationBarColor(this))

fun Activity.setNavigationbarColor(color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        this.window.navigationBarColor = color
        setLightNavigationbarAuto(color)
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
