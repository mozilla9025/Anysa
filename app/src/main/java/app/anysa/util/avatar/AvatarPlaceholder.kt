package app.anysa.util.avatar

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import app.anysa.util.extensions.replaceExtraSpaces


object AvatarPlaceholder {

    @JvmOverloads
    fun build(profileId: Long?, fullname: String, font: Typeface? = null): Drawable {
        val generator = ColorGenerator.MATERIAL
        val color = profileId?.let { generator.getColor(it) } ?: 1

        val s1: String
        var s2 = ""
        s1 = fullname[0].toString()
        try {
            if (fullname.replaceExtraSpaces().contains(" ")) {
                s2 = fullname[fullname.indexOf(" ") + 1].toString()
            } else {
                s2 = fullname[1].toString()
            }
        } catch (ignored: Exception) {
        }

        return TextDrawable.builder()
                .beginConfig()
                .width(500)
                .height(500)
                .textColor(color)
                .useFont(font)
                .fontSize(150)
                .bold()
                .toUpperCase()
                .endConfig()
                .buildRect(s1 + s2, Color.parseColor("#f8f8f8"))
    }
}
