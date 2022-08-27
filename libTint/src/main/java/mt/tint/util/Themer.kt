package mt.tint.util

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager.TaskDescription
import android.content.Context
import android.os.Build
import android.view.View
import android.view.WindowInsetsController
import androidx.annotation.ColorInt
import androidx.appcompat.widget.Toolbar
import mt.pref.ThemeColor
import mt.pref.VALUES_CHANGED
import mt.tint.viewtint.setMenuColor_White
import mt.tint.viewtint.setToolbarColorAuto
import mt.tint.viewtint.tint
import mt.util.color.isColorLight
import mt.util.color.stripAlpha

/**
 * @author Karim Abou Zeid (kabouzeid)
 */
object Themer {

    @SuppressLint("CommitPrefEdits")
    fun didThemeValuesChange(context: Context, since: Long): Boolean {
        return ThemeColor.isConfigured(context) &&
            ThemeColor.mPreferences(context).getLong(VALUES_CHANGED, -1) > since
    }

    fun setStatusbarColorAuto(activity: Activity) =
        setStatusbarColor(activity, ThemeColor.statusBarColor(activity))

    fun setStatusbarColor(activity: Activity, color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.window.statusBarColor = color
            setLightStatusbarAuto(activity, color)
        }
    }

    fun setLightStatusbarAuto(activity: Activity, bgColor: Int) =
        setLightStatusbar(activity, isColorLight(bgColor))

    fun setLightStatusbar(activity: Activity, enabled: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val wic = activity.window.insetsController
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
            val decorView = activity.window.decorView.run {
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

    fun setLightNavigationbar(activity: Activity, enabled: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            activity.window.insetsController?.let { insetsController ->
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
            activity.window.decorView.run {
                val visibility = if (enabled) {
                    systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
                } else {
                    systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR.inv()
                }
                systemUiVisibility = visibility
            }
        }
    }

    fun setLightNavigationbarAuto(activity: Activity, bgColor: Int) =
        setLightNavigationbar(activity, isColorLight(bgColor))

    fun setNavigationbarColorAuto(activity: Activity) =
        setNavigationbarColor(activity, ThemeColor.navigationBarColor(activity))

    fun setNavigationbarColor(activity: Activity, color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.window.navigationBarColor = color
            setLightNavigationbarAuto(activity, color)
        }
    }

    fun setActivityToolbarColorAuto(activity: Activity, toolbar: Toolbar) =
        setActivityToolbarColor(
            activity,
            toolbar,
            ThemeColor.primaryColor(activity)
        )

    fun setActivityToolbarColor(activity: Activity, toolbar: Toolbar, color: Int) {
        toolbar.setBackgroundColor(color)
        setMenuColor_White(activity, toolbar, toolbar.menu)
        setToolbarColorAuto(activity, toolbar, null, color)
    }

    fun setTaskDescriptionColorAuto(activity: Activity) =
        setTaskDescriptionColor(activity, ThemeColor.primaryColor(activity))

    fun setTaskDescriptionColor(activity: Activity, @ColorInt color: Int) {
        var color = color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Task description requires fully opaque color
            color = stripAlpha(color)
            // Sets color of entry in the system recents page
            activity.setTaskDescription(TaskDescription(activity.title as String, null, color))
        }
    }

    fun setTint(view: View, @ColorInt color: Int) = view.tint(color, false)
    fun setBackgroundTint(view: View, @ColorInt color: Int) = view.tint(color, true)
}
