/**
 * @author Karim Abou Zeid (kabouzeid)
 */
@file:JvmName("TextColor")

package mt.util.color

import android.content.Context
import androidx.annotation.ColorInt
import androidx.appcompat.R
import androidx.core.content.ContextCompat

/**
 * @param dark true if dark
 */
@ColorInt
fun getPrimaryTextColor(context: Context, dark: Boolean): Int =
    ContextCompat.getColor(
        context,
        if (dark) R.color.primary_text_default_material_light
        else R.color.primary_text_default_material_dark
    )

/**
 * @param dark true if dark
 */
@ColorInt
fun getSecondaryTextColor(context: Context, dark: Boolean): Int =
    ContextCompat.getColor(
        context,
        if (dark) R.color.secondary_text_default_material_light
        else R.color.secondary_text_default_material_dark
    )

/**
 * @param dark true if dark
 */
@ColorInt
fun getPrimaryDisabledTextColor(context: Context, dark: Boolean): Int =
    ContextCompat.getColor(
        context,
        if (dark) R.color.primary_text_disabled_material_light
        else R.color.primary_text_disabled_material_dark
    )

/**
 * @param dark true if dark
 */
@ColorInt
fun getSecondaryDisabledTextColor(context: Context, dark: Boolean): Int =
    ContextCompat.getColor(
        context,
        if (dark) R.color.secondary_text_disabled_material_light
        else R.color.secondary_text_disabled_material_dark
    )
