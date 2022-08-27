package mt.pref

import android.content.Context
import android.graphics.Color
import androidx.annotation.CheckResult
import androidx.annotation.ColorInt
import androidx.appcompat.R
import mt.pref.internal.ThemeStore
import mt.util.color.resolveColor
import mt.util.color.shiftColor

/**
 * a class to access stored preference
 * @author Aidan Follestad (afollestad), Karim Abou Zeid (kabouzeid), che_56 (modified)
 */
object ThemeColor {

    fun editTheme(context: Context): ThemeStore = ThemeStore.edit(context)

    @CheckResult
    @ColorInt
    fun primaryColor(context: Context): Int {
        return ThemeStore(context).pref.getInt(
            KEY_PRIMARY_COLOR,
            resolveColor(context, R.attr.colorPrimary, Color.parseColor("#455A64"))
        )
    }

    @CheckResult
    @ColorInt
    private fun primaryColorDark(context: Context): Int =
        shiftColor(primaryColor(context), 0.9f)

    @CheckResult
    @ColorInt
    fun accentColor(context: Context): Int {
        return ThemeStore(context).pref.getInt(
            KEY_ACCENT_COLOR,
            resolveColor(context, R.attr.colorAccent, Color.parseColor("#263238"))
        )
    }

    @CheckResult
    fun coloredStatusBar(context: Context): Boolean {
        return ThemeStore(context).pref.getBoolean(
            KEY_APPLY_PRIMARYDARK_STATUSBAR,
            true
        )
    }

    @CheckResult
    fun coloredNavigationBar(context: Context): Boolean {
        return ThemeStore(context).pref.getBoolean(
            KEY_APPLY_PRIMARY_NAVBAR,
            false
        )
    }

    @CheckResult
    @ColorInt
    fun navigationBarColor(context: Context): Int {
        return if (!coloredNavigationBar(context)) {
            Color.BLACK
        } else primaryColor(
            context
        )
    }

    @CheckResult
    @ColorInt
    fun statusBarColor(context: Context): Int {
        return if (!coloredStatusBar(context)) {
            Color.BLACK
        } else primaryColorDark(context)
    }
}
