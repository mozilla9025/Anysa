package app.anysa.ui.modules.main.contactsnav.contacts

import android.view.View
import app.anysa.R
import app.anysa.databinding.ItemContactBinding
import app.anysa.domain.pojo.User
import app.anysa.ui.base.abs.AbsRecyclerViewAdapter

class LinearContactsAdapter : ContactsAdapter<ItemContactBinding, LinearContactsAdapter.ViewHolder>() {

    override fun provideLayoutId(viewType: Int) = R.layout.item_contact
    override fun getViewHolder(view: View, viewType: Int) = ViewHolder(view)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        getItemAt(holder.adapterPosition)?.let {
            holder.bind(it, holder.adapterPosition)
        }
    }

    class ViewHolder(view: View) : AbsRecyclerViewAdapter.AbsViewHolder<User, ItemContactBinding>(view) {

        override fun bind(item: User, binding: ItemContactBinding, position: Int) {
            binding.user = item
            binding.executePendingBindings()
        }
    }
}