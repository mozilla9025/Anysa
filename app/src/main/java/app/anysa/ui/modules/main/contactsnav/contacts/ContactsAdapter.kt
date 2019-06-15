package app.anysa.ui.modules.main.contactsnav.contacts

import androidx.databinding.ViewDataBinding
import app.anysa.domain.pojo.User
import app.anysa.ui.base.abs.AbsRecyclerViewAdapter

abstract class ContactsAdapter<DB : ViewDataBinding, VH : AbsRecyclerViewAdapter.AbsViewHolder<User, DB>>
    : AbsRecyclerViewAdapter<User, DB, VH>() {

    override fun areItemsTheSame(oldItem: User, newItem: User) = oldItem.id == newItem.id
    override fun areContentTheSame(oldItem: User, newItem: User) = oldItem.userName == newItem.userName

}