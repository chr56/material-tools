/**
 * @author Karim Abou Zeid (kabouzeid)
 */
@file:JvmName("TextColor")

package mt.util.color

import android.content.Context
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import mt.color.R


@ColorInt
fun Context.primaryTextColor(darkmode: Boolean): Int =
    ContextCompat.getColor(
        this,
        if (darkmode) R.color.primary_text_default_darkmode
        else R.color.primary_text_default_lightmode
    )

@ColorInt
fun Context.secondaryTextColor(darkmode: Boolean): Int =
    ContextCompat.getColor(
        this,
        if (darkmode) R.color.secondary_text_default_darkmode
        else R.color.secondary_text_default_lightmode
    )

@ColorInt
fun Context.primaryDisabledTextColor(darkmode: Boolean): Int =
    ContextCompat.getColor(
        this,
        if (darkmode) R.color.primary_text_disabled_darkmode
        else R.color.primary_text_disabled_lightmode
    )

@ColorInt
fun Context.secondaryDisabledTextColor(darkmode: Boolean): Int =
    ContextCompat.getColor(
        this,
        if (darkmode) R.color.secondary_text_disabled_darkmode
        else R.color.secondary_text_disabled_lightmode
    )

@ColorInt
fun Context.primaryTextColor(@ColorInt bgColor: Int): Int =
    primaryTextColor(!isColorLight(bgColor))

@ColorInt
fun Context.secondaryTextColor(@ColorInt bgColor: Int): Int =
    secondaryTextColor(!isColorLight(bgColor))

@ColorInt
fun Context.primaryDisabledTextColor(@ColorInt bgColor: Int): Int =
    primaryDisabledTextColor(!isColorLight(bgColor))

@ColorInt
fun Context.secondaryDisabledTextColor(@ColorInt bgColor: Int): Int =
    secondaryDisabledTextColor(!isColorLight(bgColor))

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
