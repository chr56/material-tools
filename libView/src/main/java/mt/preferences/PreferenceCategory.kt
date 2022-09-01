/*
 * Copyright (c) 2022 chr_56 & Abou Zeid (kabouzeid) (original author)
 */
package mt.preferences

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.widget.TextView
import androidx.preference.PreferenceCategory
import androidx.preference.PreferenceViewHolder
import mt.pref.ThemeColor.accentColor
import mt.util.resolveAttr
import mt.views.R

class PreferenceCategory : PreferenceCategory {

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

    override fun onBindViewHolder(holder: PreferenceViewHolder) {
        super.onBindViewHolder(holder)
        (holder.itemView as TextView).setTextColor(accentColor(context))
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        layoutResource = R.layout.preference_category
    }

    companion object {
        private fun defStyleAttr(context: Context) =
            context.resolveAttr(
                androidx.preference.R.attr.preferenceCategoryStyle,
                android.R.attr.preferenceCategoryStyle
            )
    }
}
