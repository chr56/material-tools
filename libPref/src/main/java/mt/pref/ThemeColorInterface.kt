package mt.pref

import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes

/**
 * @author Aidan Follestad (afollestad), Karim Abou Zeid (kabouzeid)
 */
internal interface ThemeColorInterface {
    // Primary colors
    fun primaryColor(@ColorInt color: Int): ThemeColor?
    fun primaryColorRes(@ColorRes colorRes: Int): ThemeColor?
    fun primaryColorAttr(@AttrRes colorAttr: Int): ThemeColor?
//    fun autoGeneratePrimaryDark(autoGenerate: Boolean): ThemeColor?
//    fun primaryColorDark(@ColorInt color: Int): ThemeColor?
//    fun primaryColorDarkRes(@ColorRes colorRes: Int): ThemeColor?
//    fun primaryColorDarkAttr(@AttrRes colorAttr: Int): ThemeColor?

    // Accent colors
    fun accentColor(@ColorInt color: Int): ThemeColor?
    fun accentColorRes(@ColorRes colorRes: Int): ThemeColor?
    fun accentColorAttr(@AttrRes colorAttr: Int): ThemeColor?

//    // Status bar color
//    fun statusBarColor(@ColorInt color: Int): ThemeColor?
//    fun statusBarColorRes(@ColorRes colorRes: Int): ThemeColor?
//    fun statusBarColorAttr(@AttrRes colorAttr: Int): ThemeColor?
//
//    // Navigation bar color
//    fun navigationBarColor(@ColorInt color: Int): ThemeColor?
//    fun navigationBarColorRes(@ColorRes colorRes: Int): ThemeColor?
//    fun navigationBarColorAttr(@AttrRes colorAttr: Int): ThemeColor?
//
//    // Primary text color
//    fun textColorPrimary(@ColorInt color: Int): ThemeColor?
//    fun textColorPrimaryRes(@ColorRes colorRes: Int): ThemeColor?
//    fun textColorPrimaryAttr(@AttrRes colorAttr: Int): ThemeColor?
//    fun textColorPrimaryInverse(@ColorInt color: Int): ThemeColor?
//    fun textColorPrimaryInverseRes(@ColorRes colorRes: Int): ThemeColor?
//    fun textColorPrimaryInverseAttr(@AttrRes colorAttr: Int): ThemeColor?
//
//    // Secondary text color
//    fun textColorSecondary(@ColorInt color: Int): ThemeColor?
//    fun textColorSecondaryRes(@ColorRes colorRes: Int): ThemeColor?
//    fun textColorSecondaryAttr(@AttrRes colorAttr: Int): ThemeColor?
//    fun textColorSecondaryInverse(@ColorInt color: Int): ThemeColor?
//    fun textColorSecondaryInverseRes(@ColorRes colorRes: Int): ThemeColor?
//    fun textColorSecondaryInverseAttr(@AttrRes colorAttr: Int): ThemeColor?

    // Toggle configurations
    fun coloredStatusBar(colored: Boolean): ThemeColor?
    fun coloredNavigationBar(applyToNavBar: Boolean): ThemeColor?

    // Commit/apply
    fun commit()
}
