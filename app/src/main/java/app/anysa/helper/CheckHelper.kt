package app.anysa.helper

import android.content.Context
import android.text.TextUtils
import android.util.Patterns
import app.anysa.R
import app.anysa.util.extensions.replaceExtraSpaces


class CheckHelper(var context: Context?) {

    fun isPhoneNumberValid(phoneNumber: String): CheckResult {
        val regex = Regex("^(?:\\+?86)?1(?:3\\d{3}|5[^4\\D]\\d{2}|8\\d{3}|7(?:[35678]\\d{2}|4(?:0\\d|1[0-2]|9\\d))|9[189]\\d{2}|66\\d{2})\\d{6}\$")

        if (!phoneNumber.replace(" ", "").matches(regex))
            return CheckResult(false, phoneNumber, context!!.getString(R.string.error_invalid_phone_number))
        else
            return CheckResult(true, phoneNumber.replace("+86", "").replace(" ", "").trim())
    }

    fun arePasswordsTheSame(password1: String, password2: String): CheckResult {
        if (password1 == password2) {
            return CheckResult(true, password1)
        }
        return CheckResult(false, password1, context!!.getString(R.string.fragment_change_password_error_passwords_not_match))
    }

    fun isPasswordValid(password: String, emptyFieldMessage: String = context!!.getString(R.string.error_field_is_empty)): CheckResult {

        val MIN_PASSWORD_LENGTH = 8

        if (TextUtils.isEmpty(password)) {
            return CheckResult(false, password, emptyFieldMessage)
        }

        if (password.length < MIN_PASSWORD_LENGTH) {
            return CheckResult(false, password,
                    String.format(context!!.getString(R.string.error_password_must_be_at_least_characters_long), MIN_PASSWORD_LENGTH))
        }

        return CheckResult(true, password)
//        var capitalFlag = false
//        var lowerCaseFlag = false
//        var numberFlag = false
//
//        var ch: Char
//
//        for (i in 0 until password.length) {
//            ch = password[i]
//
//            if (Character.isDigit(ch)) {
//                numberFlag = true
//            } else if (Character.isUpperCase(ch)) {
//                capitalFlag = true
//            } else if (Character.isLowerCase(ch)) {
//                lowerCaseFlag = true
//            }
//
//
//            if (numberFlag && capitalFlag && lowerCaseFlag)
//                break
//        }
//
//        if (!numberFlag) {
//            return CheckResult(false, password,
//                    context!!.getString(R.string.error_password_must_contain_one_number))
//        }
//
//        if (!lowerCaseFlag) {
//            return CheckResult(false, password,
//                    context!!.getString(R.string.error_password_must_contain_at_least_1_lower))
//        }
//
//        return if (!capitalFlag) {
//            CheckResult(false, password,
//                    context!!.getString(R.string.error_password_must_contain_at_least_1_upper))

    }

    fun isNameValid(name: String, isRequired: Boolean = false): CheckResult {
        val name = name.replaceExtraSpaces()

        if (!isRequired && name.isNullOrEmpty())
            return CheckResult(true, "")


        val checkFieldIsEmpty = checkFieldIsEmpty(name)
        if (!checkFieldIsEmpty.isValid) return checkFieldIsEmpty

        if (name.length < 2) {
            return CheckResult(false, name, context!!.getString(R.string.error_name_too_short))
        }
        return if (name.length > 50) {
            CheckResult(false, name, context!!.getString(R.string.error_name_too_long))
        } else CheckResult(true, name)
    }

    fun isBioValid(bio: String, isRequired: Boolean = false): CheckResult {
        val bio = bio.replaceExtraSpaces()

        if (!isRequired && bio.isNullOrEmpty())
            return CheckResult(true, "")


        val checkFieldIsEmpty = checkFieldIsEmpty(bio)
        if (!checkFieldIsEmpty.isValid) return checkFieldIsEmpty

        return if (bio.length > 255) {
            CheckResult(false, bio, context!!.getString(R.string.error_bio_too_long))
        } else CheckResult(true, bio)
    }

    fun checkFieldIsEmpty(s: String, isRequired: Boolean = false): CheckResult {
        val s = s.replaceExtraSpaces()

        if (!isRequired && s.isNullOrEmpty())
            return CheckResult(true, "")

        return if (s.isNullOrEmpty()) CheckResult(false, s, context!!.getString(R.string.error_field_is_empty)) else CheckResult(true, s)

    }

    fun checkEmailValid(email: String, isRequired: Boolean = false): CheckResult {
        val email = email.replaceExtraSpaces()

        if (!isRequired && email.isNullOrEmpty())
            return CheckResult(true, "")

        val matches = Patterns.EMAIL_ADDRESS.matcher(email).matches()

        return if (matches) {
            CheckResult(true, email)
        } else {
            CheckResult(false, email, context!!.getString(R.string.error_ivalid_email_format))
        }
    }

    companion object {


        fun isValidEmail(target: CharSequence?): Boolean {
            return target != null && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()
        }
    }
}
