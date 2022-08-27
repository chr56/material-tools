package util.mdcolor.pref

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import androidx.annotation.AttrRes
import androidx.annotation.CheckResult
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.IntRange
import androidx.appcompat.R
import androidx.core.content.ContextCompat
import util.mddesign.color.shiftColor

/**
 * @author Aidan Follestad (afollestad), Karim Abou Zeid (kabouzeid), che_56 (modified)
 */
class ThemeColor
private constructor(private val mContext: Context) : ThemeColorInterface {

    private val mEditor: SharedPreferences.Editor = mPreferences(mContext).edit()

    override fun primaryColor(@ColorInt color: Int): ThemeColor {
        mEditor.putInt(KEY_PRIMARY_COLOR, color)
        return this
    }

    override fun primaryColorRes(@ColorRes colorRes: Int): ThemeColor {
        return primaryColor(ContextCompat.getColor(mContext, colorRes))
    }

    override fun primaryColorAttr(@AttrRes colorAttr: Int): ThemeColor {
        return primaryColor(resolveColor(mContext, colorAttr))
    }

    override fun accentColor(@ColorInt color: Int): ThemeColor {
        mEditor.putInt(KEY_ACCENT_COLOR, color)
        return this
    }

    override fun accentColorRes(@ColorRes colorRes: Int): ThemeColor {
        return accentColor(ContextCompat.getColor(mContext, colorRes))
    }

    override fun accentColorAttr(@AttrRes colorAttr: Int): ThemeColor {
        return accentColor(resolveColor(mContext, colorAttr))
    }

    override fun coloredStatusBar(colored: Boolean): ThemeColor {
        mEditor.putBoolean(KEY_APPLY_PRIMARYDARK_STATUSBAR, colored)
        return this
    }

    override fun coloredNavigationBar(applyToNavBar: Boolean): ThemeColor {
        mEditor.putBoolean(KEY_APPLY_PRIMARY_NAVBAR, applyToNavBar)
        return this
    }

    // Commit method
    override fun commit() {
        mEditor.putLong(VALUES_CHANGED, System.currentTimeMillis())
            .putBoolean(IS_CONFIGURED_KEY, true)
            .commit()
    }

    /**
     * **Dangerous !**, this reset all SharedPreferences!
     */
    fun clearAllPreference() {
        mEditor.clear().commit()
    }

    companion object {

        fun editTheme(context: Context): ThemeColor = ThemeColor(context)

        // Static getters
        @CheckResult
        fun mPreferences(context: Context): SharedPreferences {
            return context.getSharedPreferences(
                CONFIG_PREFS_KEY_DEFAULT,
                Context.MODE_PRIVATE
            )
        }

        @CheckResult
        fun isConfigured(context: Context): Boolean {
            return mPreferences(context).getBoolean(IS_CONFIGURED_KEY, false)
        }

        @SuppressLint("ApplySharedPref")
        fun isConfigured(
            context: Context,
            @IntRange(from = 0, to = Int.MAX_VALUE.toLong()) version: Int
        ): Boolean {
            val prefs = mPreferences(context)
            val lastVersion = prefs.getInt(IS_CONFIGURED_VERSION_KEY, -1)
            if (version > lastVersion) {
                prefs.edit().putInt(IS_CONFIGURED_VERSION_KEY, version).commit()
                return false
            }
            return true
        }

        // Access
        @CheckResult
        @ColorInt
        fun primaryColor(context: Context): Int {
            return mPreferences(context).getInt(
                KEY_PRIMARY_COLOR,
                resolveColor(context, R.attr.colorPrimary, Color.parseColor("#455A64"))
            )
        }

        @CheckResult
        @ColorInt
        fun accentColor(context: Context): Int {
            return mPreferences(context).getInt(
                KEY_ACCENT_COLOR,
                resolveColor(context, R.attr.colorAccent, Color.parseColor("#263238"))
            )
        }

        @CheckResult
        fun coloredStatusBar(context: Context): Boolean {
            return mPreferences(context).getBoolean(
                KEY_APPLY_PRIMARYDARK_STATUSBAR,
                true
            )
        }

        @CheckResult
        fun coloredNavigationBar(context: Context): Boolean {
            return mPreferences(context).getBoolean(
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

        private fun primaryColorDark(context: Context): Int =
            shiftColor(primaryColor(context), 0.9f)

        @CheckResult
        @ColorInt
        fun textColorPrimary(context: Context): Int {
            return mPreferences(context).getInt(
                KEY_TEXT_COLOR_PRIMARY,
                resolveColor(context, android.R.attr.textColorPrimary)
            )
        }

        @CheckResult
        @ColorInt
        fun textColorSecondary(context: Context): Int {
            return mPreferences(context).getInt(
                KEY_TEXT_COLOR_SECONDARY,
                resolveColor(context, android.R.attr.textColorSecondary)
            )
        }

        fun markChanged(context: Context) {
            ThemeColor(context).commit()
        }

        // Util
        internal fun resolveColor(context: Context, @AttrRes attr: Int, fallback: Int = 0): Int {
            val a = context.theme.obtainStyledAttributes(intArrayOf(attr))
            return try {
                a.getColor(0, fallback)
            } finally {
                a.recycle()
            }
        }
    }
}
