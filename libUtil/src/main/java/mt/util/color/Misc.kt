package mt.util.color

import android.content.Context
import androidx.annotation.AttrRes

@JvmOverloads
fun resolveColor(context: Context, @AttrRes attr: Int, fallback: Int = 0): Int {
    val a = context.theme.obtainStyledAttributes(intArrayOf(attr))
    return try {
        a.getColor(0, fallback)
    } finally {
        a.recycle()
    }
}

fun isWindowBackgroundDark(context: Context): Boolean {
    return !isColorLight(resolveColor(context, android.R.attr.windowBackground))
}
