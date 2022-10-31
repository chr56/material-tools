@file:JvmName("ThemeColorKt.kt")

package mt.pref

import android.content.Context
import androidx.annotation.CheckResult
import androidx.annotation.ColorInt


@CheckResult
@ColorInt
fun Context.primaryColor(): Int = ThemeColor.primaryColor(this)


@CheckResult
@ColorInt
fun Context.accentColor(): Int = ThemeColor.accentColor(this)
