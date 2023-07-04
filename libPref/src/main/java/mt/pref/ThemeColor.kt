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
import mt.pref.internal.MonetColor.MonetColorPalette
import mt.pref.internal.MonetColor.defaultMonetAccentColor
import mt.pref.internal.MonetColor.defaultMonetPrimaryColor
import mt.pref.internal.ThemeStore
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
        if (cachedPrimaryColor < 0) updateCachedPrimaryColor(context) else cachedPrimaryColor

    @CheckResult
    @ColorInt
    private fun primaryColorDark(context: Context): Int =
        shiftColor(primaryColor(context), 0.9f)

    @CheckResult
    @ColorInt
    fun accentColor(context: Context): Int =
        if (cachedAccentColor < 0) updateCachedAccentColor(context) else cachedAccentColor

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

    @RequiresApi(S)
    fun preferredMonetPrimaryColor(context: Context) =
        MonetColorPalette(
            ThemeStore(context).pref.getInt(KEY_MONET_PRIMARY_COLOR, defaultMonetPrimaryColor.value)
        ).color(context)

    @RequiresApi(S)
    fun preferredMonetAccentColor(context: Context) =
        MonetColorPalette(
            ThemeStore(context).pref.getInt(KEY_MONET_ACCENT_COLOR, defaultMonetAccentColor.value)
        ).color(context)


    @get:ColorInt
    internal var cachedPrimaryColor: Int = -1

    @get:ColorInt
    internal var cachedAccentColor: Int = -1

    @ColorInt
    internal fun updateCachedPrimaryColor(context: Context): Int {
        val pref = ThemeStore(context).pref
        val primaryColor =
            if (SDK_INT >= S && pref.getBoolean(KEY_ENABLE_MONET, false)) {
                preferredMonetPrimaryColor(context)
            } else {
                pref.getInt(
                    KEY_PRIMARY_COLOR,
                    context.getColor(mt.color.R.color.md_blue_A400)
                )
            }
        cachedPrimaryColor = primaryColor
        return primaryColor
    }

    @ColorInt
    internal fun updateCachedAccentColor(context: Context): Int {
        val pref = ThemeStore(context).pref
        val accentColor =
            if (SDK_INT >= S && pref.getBoolean(KEY_ENABLE_MONET, false)) {
                preferredMonetAccentColor(context)
            } else {
                pref.getInt(
                    KEY_ACCENT_COLOR,
                    context.getColor(mt.color.R.color.md_yellow_900)
                )
            }
        cachedAccentColor = accentColor
        return accentColor
    }

    internal fun updateCachedColor(context: Context) {
        updateCachedPrimaryColor(context)
        updateCachedAccentColor(context)
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
