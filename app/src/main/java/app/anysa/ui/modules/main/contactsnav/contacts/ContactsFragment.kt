package app.anysa.ui.modules.main.contactsnav.contacts

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import app.anysa.R
import app.anysa.databinding.FragmentContactsBinding
import app.anysa.domain.pojo.User
import app.anysa.ui.base.abs.AbsFragment
import app.anysa.util.annotation.RequiresView
import app.anysa.util.annotation.RequiresViewModel
import app.anysa.util.app_bar.AppBarStateChangeListener
import app.anysa.util.navigation.NavigationUtils
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.fragment_register.*


@RequiresView(R.layout.fragment_contacts)
@RequiresViewModel(ContactsViewModel::class)
class ContactsFragment : AbsFragment<ContactsViewModel, FragmentContactsBinding>() {

    override fun onBound(binding: FragmentContactsBinding?) {
        super.onBound(binding)

        initRvLinear()

        binding?.apply {
            ibViewGrid.setOnClickListener { initRvGrid() }
            ibViewList.setOnClickListener { initRvLinear() }
            appBar.addOnOffsetChangedListener(object : AppBarStateChangeListener() {
                override fun onStateChanged(appBarLayout: AppBarLayout, state: State) {
                    when (state) {
                        State.COLLAPSED -> view_appbar_shadow.visibility = View.VISIBLE
                        else -> view_appbar_shadow.visibility = View.GONE
                    }
                }
            })
            toolbarView.setOnActionClickListener {
                NavigationUtils.navigate(view!!, ContactsFragmentDirections.actionContactsFragmentToAddContactFragment())
            }
        }
    }


    private fun initRvLinear() {
        binding?.rvContacts?.apply {
            layoutManager = LinearLayoutManager(context)
            val contactsAdapter = LinearContactsAdapter()
            adapter = contactsAdapter

            contactsAdapter.add(User(1, "Dmitriy", avatarUrl = "https://avatars.mds.yandex.net/get-kino-vod-films-gallery/28788/47e2fd514411e18b76af786d7417062d/280x178_2"))
            contactsAdapter.add(User(2, "Dmitriy124", avatarUrl = "https://avatars.mds.yandex.net/get-kino-vod-films-gallery/28788/47e2fd514411e18b76af786d7417062d/280x178_2"))
            contactsAdapter.add(User(3, "Dmitriy123412", avatarUrl = "https://avatars.mds.yandex.net/get-kino-vod-films-gallery/28788/47e2fd514411e18b76af786d7417062d/280x178_2"))
            contactsAdapter.add(User(4, "1Dmitriy124123"))
            contactsAdapter.add(User(5, "1Dmitri 123123y", avatarUrl = "https://avatars.mds.yandex.net/get-kino-vod-films-gallery/28788/47e2fd514411e18b76af786d7417062d/280x178_2"))
            contactsAdapter.add(User(6, "1Dmitri12 3 y"))
            contactsAdapter.add(User(7, "1Dmitriy12312312  "))
            contactsAdapter.add(User(8, "1Dmitriy", avatarUrl = "https://avatars.mds.yandex.net/get-kino-vod-films-gallery/28788/47e2fd514411e18b76af786d7417062d/280x178_2"))
            contactsAdapter.add(User(9, "1Dmitriy124", avatarUrl = "https://avatars.mds.yandex.net/get-kino-vod-films-gallery/28788/47e2fd514411e18b76af786d7417062d/280x178_2"))
            contactsAdapter.add(User(10, "1Dmitr3iy123412", avatarUrl = "https://avatars.mds.yandex.net/get-kino-vod-films-gallery/28788/47e2fd514411e18b76af786d7417062d/280x178_2"))
            contactsAdapter.add(User(11, "1Dmitriy124123"))
            contactsAdapter.add(User(12, "1Dmitri 123123y", avatarUrl = "https://avatars.mds.yandex.net/get-kino-vod-films-gallery/28788/47e2fd514411e18b76af786d7417062d/280x178_2"))
            contactsAdapter.add(User(13, "1Dmitri12 3 y"))
            contactsAdapter.add(User(14, "1Dmitriy12312312  "))
        }
    }

    private fun initRvGrid() {
        binding?.rvContacts?.apply {
            layoutManager = GridLayoutManager(context, 2)
            val contactsAdapter = GridContactsAdapter()
            adapter = contactsAdapter

            contactsAdapter.add(User(1, "Dmitriy", avatarUrl = "https://avatars.mds.yandex.net/get-kino-vod-films-gallery/28788/47e2fd514411e18b76af786d7417062d/280x178_2"))
            contactsAdapter.add(User(2, "Dmitriy124", avatarUrl = "https://avatars.mds.yandex.net/get-kino-vod-films-gallery/28788/47e2fd514411e18b76af786d7417062d/280x178_2"))
            contactsAdapter.add(User(3, "Dmitriy123412", avatarUrl = "https://avatars.mds.yandex.net/get-kino-vod-films-gallery/28788/47e2fd514411e18b76af786d7417062d/280x178_2"))
            contactsAdapter.add(User(4, "1Dmitriy124123"))
            contactsAdapter.add(User(5, "1Dmitri 123123y", avatarUrl = "https://avatars.mds.yandex.net/get-kino-vod-films-gallery/28788/47e2fd514411e18b76af786d7417062d/280x178_2"))
            contactsAdapter.add(User(6, "1Dmitri12 3 y"))
            contactsAdapter.add(User(7, "1Dmitriy12312312  "))
            contactsAdapter.add(User(8, "1Dmitriy", avatarUrl = "https://avatars.mds.yandex.net/get-kino-vod-films-gallery/28788/47e2fd514411e18b76af786d7417062d/280x178_2"))
            contactsAdapter.add(User(9, "1Dmitriy124", avatarUrl = "https://avatars.mds.yandex.net/get-kino-vod-films-gallery/28788/47e2fd514411e18b76af786d7417062d/280x178_2"))
            contactsAdapter.add(User(10, "1Dmitr3iy123412", avatarUrl = "https://avatars.mds.yandex.net/get-kino-vod-films-gallery/28788/47e2fd514411e18b76af786d7417062d/280x178_2"))
            contactsAdapter.add(User(11, "1Dmitriy124123"))
            contactsAdapter.add(User(12, "1Dmitri 123123y", avatarUrl = "https://avatars.mds.yandex.net/get-kino-vod-films-gallery/28788/47e2fd514411e18b76af786d7417062d/280x178_2"))
            contactsAdapter.add(User(13, "1Dmitri12 3 y"))
            contactsAdapter.add(User(14, "1Dmitriy12312312  "))
        }
    }
}
