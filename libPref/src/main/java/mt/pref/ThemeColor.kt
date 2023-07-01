package mt.pref

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.S
import android.os.Handler
import android.os.Looper
import androidx.annotation.CheckResult
import androidx.annotation.ColorInt
import androidx.annotation.RequiresApi
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import mt.color.MonetColor
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
        ThemeStore.edit(context).apply(block).commit()

    fun enableMonet(context: Context): Boolean =
        ThemeStore(context).pref.getBoolean(KEY_ENABLE_MONET, false)

    @CheckResult
    @ColorInt
    fun primaryColor(context: Context): Int =
        if (!enableMonet(context) || SDK_INT < S)
            ThemeStore(context).pref.getInt(
                KEY_PRIMARY_COLOR,
                resolveColor(
                    context,
                    androidx.appcompat.R.attr.colorPrimary,
                    context.getColor(mt.color.R.color.md_blue_A400)
                )
            )
        else monetPrimaryColor(context)

    @CheckResult
    @ColorInt
    private fun primaryColorDark(context: Context): Int =
        shiftColor(primaryColor(context), 0.9f)

    @CheckResult
    @ColorInt
    fun accentColor(context: Context): Int =
        if (!enableMonet(context) || SDK_INT < S)
            ThemeStore(context).pref.getInt(
                KEY_ACCENT_COLOR,
                resolveColor(
                    context,
                    androidx.appcompat.R.attr.colorAccent,
                    context.getColor(mt.color.R.color.md_yellow_900)
                )
            )
        else monetAccentColor(context)

    @CheckResult
    fun coloredStatusBar(context: Context): Boolean =
        ThemeStore(context).pref.getBoolean(
            KEY_APPLY_PRIMARYDARK_STATUSBAR, true
        )

    @CheckResult
    fun coloredNavigationBar(context: Context): Boolean =
        ThemeStore(context).pref.getBoolean(
            KEY_APPLY_PRIMARY_NAVBAR, false
        )

    @CheckResult
    @ColorInt
    fun navigationBarColor(context: Context): Int =
        if (coloredNavigationBar(context)) primaryColor(context) else Color.BLACK

    @CheckResult
    @ColorInt
    fun statusBarColor(context: Context): Int =
        if (coloredStatusBar(context)) primaryColorDark(context) else Color.BLACK

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


    @RequiresApi(S)
    fun monetPrimaryColor(context: Context) = MonetColor.accent1Color(context, MonetColor.DEPTH_400)

    @RequiresApi(S)
    fun monetAccentColor(context: Context) = MonetColor.accent2Color(context, MonetColor.DEPTH_700)
}
