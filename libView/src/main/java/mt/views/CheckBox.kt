/*
 * Copyright (c) 2022 Abou Zeid (kabouzeid) (original author)
 */
package mt.views

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatCheckBox
import mt.pref.ThemeColor.accentColor
import mt.tint.viewtint.setTint
import mt.util.isNightMode

/**
 * automatically self-tint CheckBox
 * @author Aidan Follestad (afollestad)
 */
class CheckBox : AppCompatCheckBox {

    @JvmOverloads
    constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = androidx.appcompat.R.attr.checkboxStyle
    ) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        val useDarker = context.isNightMode()
        setTint(accentColor(context), useDarker)
    }
}
