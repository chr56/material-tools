@file:JvmName("ToolbarColor")

package mt.util.color

import android.content.Context
import androidx.annotation.CheckResult
import androidx.annotation.ColorInt

@CheckResult
@ColorInt
fun toolbarContentColor(context: Context, @ColorInt toolbarColor: Int): Int {
    return if (isColorLight(toolbarColor)) {
        toolbarSubtitleColor(context, toolbarColor)
    } else {
        toolbarTitleColor(context, toolbarColor)
    }
}

@CheckResult
@ColorInt
fun toolbarSubtitleColor(context: Context, @ColorInt toolbarColor: Int) =
    getSecondaryTextColor(context, isColorLight(toolbarColor))

@CheckResult
@ColorInt
fun toolbarTitleColor(context: Context, @ColorInt toolbarColor: Int) =
    getPrimaryTextColor(context, isColorLight(toolbarColor))
