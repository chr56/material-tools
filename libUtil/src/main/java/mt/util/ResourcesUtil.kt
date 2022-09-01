@file:JvmName("ResourcesUtil")

package mt.util

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.util.TypedValue
import androidx.annotation.AttrRes

fun Context.isNightMode(): Boolean =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        resources.configuration.isNightModeActive
    } else {
        val nightModeFlags = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        nightModeFlags == Configuration.UI_MODE_NIGHT_YES
    }

fun Context.resolveAttr(@AttrRes attr: Int, @AttrRes fallbackAttr: Int): Int {
    val value = TypedValue()
    theme.resolveAttribute(attr, value, true)
    return if (value.resourceId != 0) {
        attr
    } else {
        fallbackAttr
    }
}
