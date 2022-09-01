/*
 * Copyright (c) 2022 Abou Zeid (kabouzeid) (original author)
 */
package mt.views

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.SwitchCompat
import mt.pref.ThemeColor.accentColor
import mt.tint.viewtint.setTint
import mt.util.isNightMode

/**
 * automatically self-tint Switch
 * @author Aidan Follestad (afollestad)
 */
class Switch : SwitchCompat {

    @JvmOverloads
    constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = androidx.appcompat.R.attr.checkboxStyle
    ) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        val useDarker = context.isNightMode() // || isWindowBackgroundDark(context)
        setTint(accentColor(context), useDarker)
    }
}
