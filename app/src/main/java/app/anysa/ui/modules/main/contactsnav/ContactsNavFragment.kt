package app.anysa.ui.modules.main.contactsnav

import app.anysa.R
import app.anysa.databinding.FragmentContactsNavBinding
import app.anysa.ui.base.abs.AbsFragment
import app.anysa.util.annotation.NavFragment
import app.anysa.util.annotation.RequiresView
import app.anysa.util.annotation.RequiresViewModel

@RequiresView(R.layout.fragment_contacts_nav)
@RequiresViewModel(ContactsNavViewModel::class)
@NavFragment
class ContactsNavFragment : AbsFragment<ContactsNavViewModel, FragmentContactsNavBinding>() {

}
