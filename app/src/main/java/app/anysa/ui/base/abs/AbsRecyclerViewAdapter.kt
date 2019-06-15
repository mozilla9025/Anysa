package app.anysa.ui.base.abs

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import java.util.*

@Suppress("MemberVisibilityCanBePrivate")
abstract class AbsRecyclerViewAdapter<T, DB : ViewDataBinding, VH : AbsRecyclerViewAdapter.AbsViewHolder<T, DB>> : BaseRecyclerViewAdapter<T, DB, VH>() {

    private val itemClickListener = ArrayList<OnItemClickListener>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        context = parent.context
        val viewHolder = getViewHolder(getView(parent, viewType), viewType)
        if (isItemClickListenerSet()) {
            viewHolder.setOnClickListener(itemClickListener)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = getItemAt(position) ?: return
        holder.bind(item, holder.adapterPosition)
    }

    override fun onBindViewHolder(holder: VH, position: Int, payloads: MutableList<Any>) {
        (payloads.firstOrNull() as? Bundle)?.let { holder.diff(it) }
                ?: super.onBindViewHolder(holder, position, payloads)
    }

    fun isItemClickListenerSet() = itemClickListener.isNotEmpty()

    fun onItemClick(action: ((T?) -> Unit)?) {
        addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(adapterPosition: Int) {
                action?.invoke(getItemAt(adapterPosition))
            }
        })
    }

    fun addOnItemClickListener(action: ((position: Int) -> Unit)?) {
        this.itemClickListener.add(object : OnItemClickListener {
            override fun onItemClicked(adapterPosition: Int) {
                action?.invoke(adapterPosition)
            }
        })
    }

    fun addOnItemClickListener(listener: OnItemClickListener) {
        this.itemClickListener.add(listener)
    }

    fun removeOnItemClickListener(listener: OnItemClickListener) {
        this.itemClickListener.remove(listener)
    }


    abstract class AbsViewHolder<T, DB : ViewDataBinding>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var clickListeners: List<OnItemClickListener>? = null
        internal val context by lazy { itemView.context }
        val binding: DB? = DataBindingUtil.bind(itemView)

        private var item: T? = null

        init {
            itemView.setOnClickListener {
                if (!ignoreClick()
                        && clickListeners != null
                        && !clickListeners!!.isEmpty()) {
                    for (clickListener in clickListeners!!) {
                        clickListener.onItemClicked(adapterPosition)
                    }
                }
            }
        }

        fun setOnClickListener(listeners: List<OnItemClickListener>) {
            this.clickListeners = listeners
        }

        fun getItem(): T? = item

        open fun ignoreClick() = false

        internal fun bind(item: T, position: Int) {
            this.item = item
            bind(item, binding ?: return, position)
        }

        abstract fun bind(item: T, binding: DB, position: Int)

        open fun diff(bundle: Bundle) {

        }
    }

    interface OnItemClickListener {
        fun onItemClicked(adapterPosition: Int)
    }
}
