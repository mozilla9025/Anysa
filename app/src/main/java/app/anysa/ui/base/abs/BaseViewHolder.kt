package app.anysa.ui.base.abs

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


open class BaseViewHolder<DB : ViewDataBinding>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val binding: DB? = DataBindingUtil.bind(itemView)

}
