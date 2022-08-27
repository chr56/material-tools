@file:JvmName("ImageViewUtil")

package mt.tint.viewtint

import android.graphics.PorterDuff
import android.os.Build
import android.widget.ImageView
import androidx.annotation.ColorInt
import mt.util.drawable.createTintedDrawable

fun ImageView.setDrawableColor(@ColorInt color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
    } else {
        if (drawable != null) {
            setImageDrawable(
                createTintedDrawable(drawable!!, color)
            )
        }
    }
}
