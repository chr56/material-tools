package mt.color

import android.content.Context
import android.os.Build.VERSION_CODES.S
import androidx.annotation.ColorInt
import androidx.annotation.IntDef
import androidx.annotation.RequiresApi

object MonetColor {

    @RequiresApi(S)
    @ColorInt
    fun accent1Color(context: Context, deep: Int): Int =
        dynasticColor(context, ACCENT1, deep)

    @RequiresApi(S)
    @ColorInt
    fun accent2Color(context: Context, deep: Int): Int =
        dynasticColor(context, ACCENT2, deep)

    @RequiresApi(S)
    @ColorInt
    fun accent3Color(context: Context, deep: Int): Int =
        dynasticColor(context, ACCENT3, deep)

    @RequiresApi(S)
    @ColorInt
    fun neutral1Color(context: Context, deep: Int): Int =
        dynasticColor(context, NEUTRAL1, deep)

    @RequiresApi(S)
    @ColorInt
    fun neutral2Color(context: Context, deep: Int): Int =
        dynasticColor(context, NEUTRAL2, deep)

    @RequiresApi(S)
    @ColorInt
    private fun dynasticColor(context: Context, @Type type: Int, deep: Int): Int {
        return when (type) {
            ACCENT1 -> when (deep) {
                0 -> context.getColor(android.R.color.system_accent1_0)
                10 -> context.getColor(android.R.color.system_accent1_10)
                50 -> context.getColor(android.R.color.system_accent1_50)
                100 -> context.getColor(android.R.color.system_accent1_100)
                200 -> context.getColor(android.R.color.system_accent1_200)
                300 -> context.getColor(android.R.color.system_accent1_300)
                400 -> context.getColor(android.R.color.system_accent1_400)
                500 -> context.getColor(android.R.color.system_accent1_500)
                600 -> context.getColor(android.R.color.system_accent1_600)
                700 -> context.getColor(android.R.color.system_accent1_700)
                800 -> context.getColor(android.R.color.system_accent1_800)
                900 -> context.getColor(android.R.color.system_accent1_900)
                1000 -> context.getColor(android.R.color.system_accent1_1000)
                else -> -1
            }

            ACCENT2 -> when (deep) {
                0 -> context.getColor(android.R.color.system_accent2_0)
                10 -> context.getColor(android.R.color.system_accent2_10)
                50 -> context.getColor(android.R.color.system_accent2_50)
                100 -> context.getColor(android.R.color.system_accent2_100)
                200 -> context.getColor(android.R.color.system_accent2_200)
                300 -> context.getColor(android.R.color.system_accent2_300)
                400 -> context.getColor(android.R.color.system_accent2_400)
                500 -> context.getColor(android.R.color.system_accent2_500)
                600 -> context.getColor(android.R.color.system_accent2_600)
                700 -> context.getColor(android.R.color.system_accent2_700)
                800 -> context.getColor(android.R.color.system_accent2_800)
                900 -> context.getColor(android.R.color.system_accent2_900)
                1000 -> context.getColor(android.R.color.system_accent2_1000)
                else -> -1
            }

            ACCENT3 -> when (deep) {
                0 -> context.getColor(android.R.color.system_accent3_0)
                10 -> context.getColor(android.R.color.system_accent3_10)
                50 -> context.getColor(android.R.color.system_accent3_50)
                100 -> context.getColor(android.R.color.system_accent3_100)
                200 -> context.getColor(android.R.color.system_accent3_200)
                300 -> context.getColor(android.R.color.system_accent3_300)
                400 -> context.getColor(android.R.color.system_accent3_400)
                500 -> context.getColor(android.R.color.system_accent3_500)
                600 -> context.getColor(android.R.color.system_accent3_600)
                700 -> context.getColor(android.R.color.system_accent3_700)
                800 -> context.getColor(android.R.color.system_accent3_800)
                900 -> context.getColor(android.R.color.system_accent3_900)
                1000 -> context.getColor(android.R.color.system_accent3_1000)
                else -> -1
            }

            NEUTRAL1 -> when (deep) {
                0 -> context.getColor(android.R.color.system_neutral1_0)
                10 -> context.getColor(android.R.color.system_neutral1_10)
                50 -> context.getColor(android.R.color.system_neutral1_50)
                100 -> context.getColor(android.R.color.system_neutral1_100)
                200 -> context.getColor(android.R.color.system_neutral1_200)
                300 -> context.getColor(android.R.color.system_neutral1_300)
                400 -> context.getColor(android.R.color.system_neutral1_400)
                500 -> context.getColor(android.R.color.system_neutral1_500)
                600 -> context.getColor(android.R.color.system_neutral1_600)
                700 -> context.getColor(android.R.color.system_neutral1_700)
                800 -> context.getColor(android.R.color.system_neutral1_800)
                900 -> context.getColor(android.R.color.system_neutral1_900)
                1000 -> context.getColor(android.R.color.system_neutral1_1000)
                else -> -1
            }

            NEUTRAL2 -> when (deep) {
                0 -> context.getColor(android.R.color.system_neutral2_0)
                10 -> context.getColor(android.R.color.system_neutral2_10)
                50 -> context.getColor(android.R.color.system_neutral2_50)
                100 -> context.getColor(android.R.color.system_neutral2_100)
                200 -> context.getColor(android.R.color.system_neutral2_200)
                300 -> context.getColor(android.R.color.system_neutral2_300)
                400 -> context.getColor(android.R.color.system_neutral2_400)
                500 -> context.getColor(android.R.color.system_neutral2_500)
                600 -> context.getColor(android.R.color.system_neutral2_600)
                700 -> context.getColor(android.R.color.system_neutral2_700)
                800 -> context.getColor(android.R.color.system_neutral2_800)
                900 -> context.getColor(android.R.color.system_neutral2_900)
                1000 -> context.getColor(android.R.color.system_neutral2_1000)
                else -> -1
            }

            else -> -1
        }
    }

    private const val ACCENT1 = 2
    private const val ACCENT2 = 4
    private const val ACCENT3 = 8
    private const val NEUTRAL1 = 16
    private const val NEUTRAL2 = 32


    @IntDef(ACCENT1, ACCENT2, ACCENT3, NEUTRAL1, NEUTRAL2)
    @Retention(AnnotationRetention.SOURCE)
    private annotation class Type
}

@RequiresApi(S)
fun monetPrimaryColor(context: Context) = MonetColor.accent1Color(context, 300)

@RequiresApi(S)
fun monetAccentColor(context: Context) = MonetColor.accent2Color(context, 600)