package app.anysa.util.input_filter

import android.text.InputFilter
import android.text.Spanned
import java.util.regex.Pattern

class ChineseNumberInputFilter : InputFilter {

    private var mPattern: Pattern = Pattern.compile("^(?:\\+?86)?1(?:3\\d{3}|5[^4\\D]\\d{2}|8\\d{3}|7(?:[35678]\\d{2}|4(?:0\\d|1[0-2]|9\\d))|9[189]\\d{2}|66\\d{2})\\d{6}\$")

    override fun filter(source: CharSequence?, start: Int, end: Int, dest: Spanned?, dstart: Int, dend: Int): CharSequence? {
        if (dest != null && dest.length > 0 && source != null && source.length > 0 &&
                dest.toString().contains(".") && source[0] == '.') {
            return ""
        }

        val matcher = mPattern.matcher(dest!!)
        return if (!matcher.matches()) "" else null
    }

}