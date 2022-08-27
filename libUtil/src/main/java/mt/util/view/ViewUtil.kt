/**
 * @author Karim Abou Zeid (kabouzeid)
 */
@file:JvmName("ViewUtil")

package mt.tint.internal

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.TransitionDrawable
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.annotation.ColorInt
import mt.util.drawable.createTransitionDrawable

fun removeOnGlobalLayoutListener(v: View, listener: OnGlobalLayoutListener?) {
    v.viewTreeObserver.removeOnGlobalLayoutListener(listener)
}

fun setBackgroundCompat(view: View, drawable: Drawable?) {
    view.setBackgroundDrawable(drawable)
}

fun setBackgroundTransition(view: View, newDrawable: Drawable): TransitionDrawable {
    val transition = createTransitionDrawable(view.background, newDrawable)
    setBackgroundCompat(view, transition)
    return transition
}

fun setBackgroundColorTransition(view: View, @ColorInt newColor: Int): TransitionDrawable {
    val oldColor = view.background
    val start = oldColor ?: ColorDrawable(view.solidColor)
    val end: Drawable = ColorDrawable(newColor)
    val transition = createTransitionDrawable(start, end)
    setBackgroundCompat(view, transition)
    return transition
}
