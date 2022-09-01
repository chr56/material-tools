/*
 * Copyright (c) 2022 chr_56 & Abou Zeid (kabouzeid) (original author)
 */
package mt.preferences

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import androidx.preference.Preference
import mt.util.resolveAttr
import mt.views.R

/**
 * self-tint
 * @author Aidan Follestad (afollestad)
 */
open class Preference : Preference {

    @JvmOverloads
    constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = defStyleAttr(context)
    ) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    ) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        layoutResource = R.layout.preference
    }

    companion object {
        private fun defStyleAttr(context: Context) =
            context.resolveAttr(
                androidx.preference.R.attr.preferenceStyle,
                android.R.attr.preferenceStyle
            )
    }
}
