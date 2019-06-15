package app.anysa.ui.modules.main.contactsnav.contacts

import app.anysa.R
import app.anysa.databinding.FragmentContactsBinding
import app.anysa.ui.base.abs.AbsFragment
import app.anysa.util.annotation.RequiresView
import app.anysa.util.annotation.RequiresViewModel


@RequiresView(R.layout.fragment_contacts)
@RequiresViewModel(ContactsViewModel::class)
class ContactsFragment : AbsFragment<ContactsViewModel, FragmentContactsBinding>(){


}
