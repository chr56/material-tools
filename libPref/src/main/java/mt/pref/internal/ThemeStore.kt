package mt.pref.internal

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.AttrRes
import androidx.annotation.CheckResult
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.IntRange
import androidx.core.content.ContextCompat
import mt.pref.*
import mt.util.color.resolveColor

/**
 * a class to store theme color preference
 * @author Aidan Follestad (afollestad), Karim Abou Zeid (kabouzeid), che_56 (modified)
 */
@Suppress("unused")
class ThemeStore internal constructor(private val mContext: Context) {

    internal val pref: SharedPreferences = mContext.getSharedPreferences(
        CONFIG_PREFS_KEY_DEFAULT,
        Context.MODE_PRIVATE
    )
    private val mEditor get() = pref.edit()

    fun primaryColor(@ColorInt color: Int): ThemeStore {
        mEditor.putInt(KEY_PRIMARY_COLOR, color)
        return this
    }

    fun primaryColorRes(@ColorRes colorRes: Int): ThemeStore {
        return primaryColor(ContextCompat.getColor(mContext, colorRes))
    }

    fun primaryColorAttr(@AttrRes colorAttr: Int): ThemeStore {
        return primaryColor(resolveColor(mContext, colorAttr))
    }

    fun accentColor(@ColorInt color: Int): ThemeStore {
        mEditor.putInt(KEY_ACCENT_COLOR, color)
        return this
    }

    fun accentColorRes(@ColorRes colorRes: Int): ThemeStore {
        return accentColor(ContextCompat.getColor(mContext, colorRes))
    }

    fun accentColorAttr(@AttrRes colorAttr: Int): ThemeStore {
        return accentColor(resolveColor(mContext, colorAttr))
    }

    fun coloredStatusBar(colored: Boolean): ThemeStore {
        mEditor.putBoolean(KEY_APPLY_PRIMARYDARK_STATUSBAR, colored)
        return this
    }

    fun coloredNavigationBar(applyToNavBar: Boolean): ThemeStore {
        mEditor.putBoolean(KEY_APPLY_PRIMARY_NAVBAR, applyToNavBar)
        return this
    }

    fun markChanged() = mEditor.commit()

    fun commit() =
        mEditor.putLong(VALUES_CHANGED, System.currentTimeMillis())
            .putBoolean(IS_CONFIGURED_KEY, true)
            .commit()

    fun apply() =
        mEditor.putLong(VALUES_CHANGED, System.currentTimeMillis())
            .putBoolean(IS_CONFIGURED_KEY, true)
            .apply()

    /**
     * **Dangerous !**, this reset all SharedPreferences!
     */
    fun clearAllPreference() {
        mEditor.clear().commit()
    }

    @CheckResult
    fun isConfigured(): Boolean {
        return pref.getBoolean(IS_CONFIGURED_KEY, false)
    }

    @SuppressLint("ApplySharedPref")
    fun isConfigured(
        @IntRange(from = 0, to = Int.MAX_VALUE.toLong()) version: Int
    ): Boolean {
        val lastVersion = pref.getInt(IS_CONFIGURED_VERSION_KEY, -1)
        if (version > lastVersion) {
            mEditor.putInt(IS_CONFIGURED_VERSION_KEY, version).commit()
            return false
        }
        return true
    }

    companion object {
        internal fun edit(context: Context): ThemeStore = ThemeStore(context)

        fun isConfigured(context: Context): Boolean =
            ThemeStore(context).isConfigured()

        fun isConfigured(
            context: Context,
            @IntRange(from = 0, to = Int.MAX_VALUE.toLong()) version: Int
        ): Boolean =
            ThemeStore(context).isConfigured(version)

        @SuppressLint("CommitPrefEdits")
        fun didThemeValuesChange(context: Context, since: Long): Boolean =
            isConfigured(context) &&
                ThemeStore(context).pref.getLong(VALUES_CHANGED, -1) > since
    }
}
