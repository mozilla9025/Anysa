package app.anysa.ui.base.abs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.anysa.util.DiffCallback
import java.util.*

@Suppress("MemberVisibilityCanBePrivate")
abstract class BaseRecyclerViewAdapter<T, DB : ViewDataBinding, VH : RecyclerView.ViewHolder>
    : RecyclerView.Adapter<VH>() {

    internal lateinit var context: Context
    private val items = ArrayList<T>()
    var comparator: Comparator<T>? = null
        set(value) {
            field = value
            setList(items)
        }
    var recyclerView: RecyclerView? = null

    abstract fun provideLayoutId(viewType: Int): Int
    abstract fun getViewHolder(view: View, viewType: Int): VH
    abstract fun areItemsTheSame(oldItem: T, newItem: T): Boolean
    open fun areContentTheSame(oldItem: T, newItem: T) = areItemsTheSame(oldItem, newItem)

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    fun getView(parent: ViewGroup, viewType: Int): View {
        val layoutId = provideLayoutId(viewType)
        val view: View
        if (layoutId != -1) {
            view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        } else {
            throw IllegalStateException("No item layout specified for " + this.javaClass.name)
        }
        return view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        context = parent.context
        return getViewHolder(getView(parent, viewType), viewType)
    }

    override fun getItemCount() = items.size

    fun getItems() = items

    fun getItemPosition(item: T) = items.indexOf(item)

    fun getItemPosition(predicate: (T) -> Boolean) = items.indexOfFirst(predicate)

    fun getItemAt(position: Int) = if (position < 0 || position >= items.size) null
    else items[position]

    fun add(item: T) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun replace(item: T, predicate: (T) -> Boolean) {
        val index = items.indexOfFirst(predicate)
        replace(item, index)
    }

    fun replace(item: T, index: Int) {
        if (index < 0) return
        items[index] = item
        notifyItemChanged(index)
    }

    fun remove(item: T) {
        val position = items.indexOf(item)
        items.remove(item)
        notifyItemRemoved(position)
    }

    fun removeAt(index: Int) {
        items.removeAt(index)
        notifyItemRemoved(index)
    }

    fun removeBy(predicate: (T) -> Boolean) {
        remove(items.find(predicate) ?: return)
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    fun addList(newItems: List<T>) {
        val mergedList = ArrayList<T>()
        mergedList.addAll(items)
        mergedList.addAll(newItems)
        setList(mergedList)
    }

    fun isEmpty() = items.isEmpty()

    open fun setList(newItems: List<T>) {
        optionalSort(newItems)
        val res = DiffUtil.calculateDiff(object : DiffCallback<T>(items, newItems) {

            override fun getNewListSize() = newItems.size

            override fun getOldListSize() = items.size

            override fun areItemsTheSame(oldItem: T, newItem: T) =
                    this@BaseRecyclerViewAdapter.areItemsTheSame(oldItem, newItem)

            override fun areContentsTheSame(oldItem: T, newItem: T) =
                    this@BaseRecyclerViewAdapter.areContentTheSame(oldItem, newItem)

        })
        rawSetList(newItems)

        res.dispatchUpdatesTo(this)
    }

    fun rawSetList(newItems: List<T>) {
        items.clear()
        items.addAll(newItems)
    }

    private fun optionalSort(newItems: List<T>) {
        if (comparator != null) {
            Collections.sort(newItems, comparator)
        }
    }
}
