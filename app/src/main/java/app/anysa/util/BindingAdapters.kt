package app.anysa.util

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import app.anysa.R
import app.anysa.domain.pojo.User
import app.anysa.ui.widget.SettingsEditText
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun setImageViewSrc(imageView: ImageView, user: User?) {
        user?.let {
//            val placeholder = AvatarPlaceholder.build(user.id.toLong(),
//                    user.username.let { user.username } ?: "##")

            Glide.with(imageView.context).load(it.avatarUrl)
                    .apply(RequestOptions()
                            .centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .override(500, 500)
                            .placeholder(R.drawable.ic_no_avatar)
                            .error(R.drawable.ic_no_avatar))
                    .into(imageView)
        }
    }


    @JvmStatic
    @BindingAdapter("app:set_text")
    fun setImageViewSrc(et: SettingsEditText, text: String?) {
        if (text != null) {
            et.text = text
        } else {
            et.text = ""
        }
    }

    @SuppressLint("SetTextI18n")
    @JvmStatic
    @BindingAdapter("app:phone_number")
    fun setPhoneNumber(tv: TextView, phone: String?) {
        if (phone != null && phone.startsWith("+86")) {
            tv.text = phone
        } else {
            tv.text = "+86$phone"
        }
    }
}
