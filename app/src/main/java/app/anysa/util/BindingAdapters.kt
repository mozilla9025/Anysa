package app.anysa.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import app.anysa.domain.pojo.User
import app.anysa.util.avatar.AvatarPlaceholder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun setImageViewSrc(imageView: ImageView, user: User?) {
        user?.let {
            val placeholder = AvatarPlaceholder.build(user.id.toLong(),
                    user.userName.let { user.userName } ?: "##")

            Glide.with(imageView.context).load(it.avatarURL)
                    .apply(RequestOptions()
                            .centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .override(500, 500)
                            .placeholder(placeholder))
                    .into(imageView)
        }
    }
}
