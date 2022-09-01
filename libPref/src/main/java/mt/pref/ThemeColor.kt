package mt.pref

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Handler
import android.os.Looper
import androidx.annotation.CheckResult
import androidx.annotation.ColorInt
import androidx.appcompat.R
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import mt.pref.internal.ThemeStore
import mt.util.color.resolveColor
import mt.util.color.shiftColor

/**
 * a class to access stored preference
 * @author Aidan Follestad (afollestad), Karim Abou Zeid (kabouzeid), che_56 (modified)
 */
object ThemeColor {

    fun editTheme(context: Context): ThemeStore = ThemeStore.edit(context)
    fun edit(context: Context, block: ThemeStore.() -> Unit) =
        ThemeStore.edit(context).apply(block)

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
        return if (coloredNavigationBar(context)) primaryColor(context)
        else Color.BLACK
    }

    @CheckResult
    @ColorInt
    fun statusBarColor(context: Context): Int {
        return if (coloredStatusBar(context)) primaryColorDark(context)
        else Color.BLACK
    }

    fun registerPreferenceChangeListener(
        l: ThemePreferenceChangeListener,
        appContext: Context,
        host: LifecycleOwner
    ) {
        val context = appContext.applicationContext
        host.lifecycle.addObserver(object : DefaultLifecycleObserver {
            val themeListener = l
            val listener =
                SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
                    Handler(Looper.myLooper() ?: Looper.getMainLooper())
                        .postDelayed(
                            {
                                when (key) {
                                    KEY_ACCENT_COLOR ->
                                        themeListener.onAccentColorChanged(accentColor(context))
                                    KEY_PRIMARY_COLOR ->
                                        themeListener.onPrimaryColorChanged(primaryColor(context))
                                    KEY_APPLY_PRIMARYDARK_STATUSBAR ->
                                        themeListener.onStatusBarTintSettingChanged(
                                            coloredStatusBar(context)
                                        )
                                    KEY_APPLY_PRIMARY_NAVBAR ->
                                        themeListener.onNavigationBarTintSettingChanged(
                                            coloredNavigationBar(context)
                                        )
                                }
                            },
                            500
                        )
                }

            override fun onCreate(owner: LifecycleOwner) {
                ThemeStore(context).pref.registerOnSharedPreferenceChangeListener(listener)
            }

            override fun onDestroy(owner: LifecycleOwner) {
                ThemeStore(context).pref.unregisterOnSharedPreferenceChangeListener(listener)
            }
        })
    }

    interface ThemePreferenceChangeListener {
        fun onAccentColorChanged(@ColorInt newColor: Int)
        fun onPrimaryColorChanged(@ColorInt newColor: Int)
        fun onStatusBarTintSettingChanged(coloredStatusBar: Boolean)
        fun onNavigationBarTintSettingChanged(coloredNavigationBar: Boolean)
    }
}
