@file:JvmName("ResourcesUtil")

package mt.util

import android.content.Context
import android.content.res.Configuration
import android.os.Build

fun Context.isNightMode(): Boolean =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        resources.configuration.isNightModeActive
    } else {
        val nightModeFlags = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        nightModeFlags == Configuration.UI_MODE_NIGHT_YES
    }
