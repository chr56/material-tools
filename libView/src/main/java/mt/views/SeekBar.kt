/*
 * Copyright (c) 2022 Abou Zeid (kabouzeid) (original author)
 */
package mt.views

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatSeekBar
import mt.pref.ThemeColor.accentColor
import mt.tint.viewtint.setTint

/**
 * automatically self-tint SeekBar
 * @author Aidan Follestad (afollestad)
 */
class SeekBar : AppCompatSeekBar {

    @JvmOverloads
    constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = androidx.appcompat.R.attr.checkboxStyle
    ) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        setTint(accentColor(context))
    }
}
