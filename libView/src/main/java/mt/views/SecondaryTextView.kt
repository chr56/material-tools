/*
 * Copyright (c) 2022 Abou Zeid (kabouzeid) (original author)
 */
package mt.views

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import mt.util.color.secondaryTextColor

/**
 * @author Aidan Follestad (afollestad)
 */
open class SecondaryTextView : AppCompatTextView {

    @JvmOverloads
    constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = androidx.appcompat.R.attr.checkboxStyle
    ) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        setTextColor(context.secondaryTextColor())
    }
}
