@file:JvmName("ToolbarUtil")

package mt.tint.viewtint

import android.content.Context
import androidx.annotation.ColorInt
import androidx.appcompat.widget.Toolbar
import mt.util.color.toolbarIconColor
import mt.util.color.toolbarSubtitleColor
import mt.util.color.toolbarTitleColor
import mt.util.drawable.createTintedDrawable

/**
 * @author Karim Abou Zeid (kabouzeid), chr_56
 */
@JvmName("setToolbarColorKt")
fun Toolbar.setToolbarTextColor(
    @ColorInt toolbarColor: Int,
    @ColorInt titleTextColor: Int,
    @ColorInt subtitleTextColor: Int
) = setToolbarTextColor(
    this,
    toolbarColor,
    titleTextColor,
    subtitleTextColor
)

fun setToolbarTextColor(
    toolbar: Toolbar?,
    @ColorInt iconColor: Int,
    @ColorInt titleTextColor: Int,
    @ColorInt subtitleTextColor: Int
) {
    if (toolbar != null) {
        // Text
        toolbar.setTitleTextColor(titleTextColor)
        toolbar.setSubtitleTextColor(subtitleTextColor)
        // Icon
        toolbar.tintNavigationIcon(iconColor)
    }
}

fun setToolbarTextColor(
    context: Context,
    toolbar: Toolbar,
    @ColorInt toolbarBackgroundColor: Int
) = setToolbarTextColor(
    toolbar,
    toolbarIconColor(context, toolbarBackgroundColor),
    toolbarTitleColor(context, toolbarBackgroundColor),
    toolbarSubtitleColor(context, toolbarBackgroundColor)
)


fun Toolbar.tintNavigationIcon(@ColorInt color: Int) {
    navigationIcon?.let {
        navigationIcon = createTintedDrawable(it, color)
    }
}


fun Toolbar.tintCollapseIcon(@ColorInt color: Int) {
    collapseIcon?.let {
        collapseIcon = createTintedDrawable(it, color)
    }
}