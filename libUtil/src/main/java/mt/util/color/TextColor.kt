/**
 * @author Karim Abou Zeid (kabouzeid)
 */
@file:JvmName("TextColor")

package mt.util.color

import android.content.Context
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import mt.color.R

/**
 * @param inDark true if the dark version wanted
 */
@Deprecated("legacy")
@ColorInt
fun getPrimaryTextColor(context: Context, inDark: Boolean): Int =
    ContextCompat.getColor(
        context,
        if (inDark) R.color.primary_text_default_lightmode
        else R.color.primary_text_default_darkmode
    )

/**
 * @param inDark true if the dark version wanted
 */
@Deprecated("legacy")
@ColorInt
fun getSecondaryTextColor(context: Context, inDark: Boolean): Int =
    ContextCompat.getColor(
        context,
        if (inDark) R.color.secondary_text_default_lightmode
        else R.color.secondary_text_default_darkmode
    )

/**
 * @param inDark true if the dark version wanted
 */
@Deprecated("legacy")
@ColorInt
fun getPrimaryDisabledTextColor(context: Context, inDark: Boolean): Int =
    ContextCompat.getColor(
        context,
        if (inDark) R.color.primary_text_disabled_lightmode
        else R.color.primary_text_disabled_darkmode
    )

/**
 * @param inDark true if the dark version wanted
 */
@Deprecated("legacy")
@ColorInt
fun getSecondaryDisabledTextColor(context: Context, inDark: Boolean): Int =
    ContextCompat.getColor(
        context,
        if (inDark) R.color.secondary_text_disabled_lightmode
        else R.color.secondary_text_disabled_darkmode
    )

@ColorInt
fun Context.primaryTextColor(darkmode: Boolean): Int =
    getPrimaryTextColor(this, !darkmode)

@ColorInt
fun Context.secondaryTextColor(darkmode: Boolean): Int =
    getSecondaryTextColor(this, !darkmode)

@ColorInt
fun Context.primaryDisabledTextColor(darkmode: Boolean): Int =
    getPrimaryDisabledTextColor(this, !darkmode)

@ColorInt
fun Context.secondaryDisabledTextColor(darkmode: Boolean): Int =
    getSecondaryDisabledTextColor(this, !darkmode)

@ColorInt
fun Context.primaryTextColor(@ColorInt bgColor: Int): Int =
    getPrimaryTextColor(this, isColorLight(bgColor))

@ColorInt
fun Context.secondaryTextColor(@ColorInt bgColor: Int): Int =
    getSecondaryTextColor(this, isColorLight(bgColor))

@ColorInt
fun Context.primaryDisabledTextColor(@ColorInt bgColor: Int): Int =
    getPrimaryDisabledTextColor(this, isColorLight(bgColor))

@ColorInt
fun Context.secondaryDisabledTextColor(@ColorInt bgColor: Int): Int =
    getSecondaryDisabledTextColor(this, isColorLight(bgColor))

@ColorInt
fun Context.primaryTextColor(): Int =
    ContextCompat.getColor(
        this,
        R.color.primary_text_default
    )

@ColorInt
fun Context.secondaryTextColor(): Int =
    ContextCompat.getColor(
        this,
        R.color.secondary_text_default
    )

@ColorInt
fun Context.primaryDisabledTextColor(): Int =
    ContextCompat.getColor(
        this,
        R.color.primary_text_disabled
    )

@ColorInt
fun Context.secondaryDisabledTextColor(): Int =
    ContextCompat.getColor(
        this,
        R.color.secondary_text_disabled
    )
