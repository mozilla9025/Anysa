package app.anysa.ui.modules.main.chatsnav

import app.anysa.R
import app.anysa.databinding.FragmentChatsNavBinding
import app.anysa.ui.base.abs.AbsFragment
import app.anysa.util.annotation.RequiresView
import app.anysa.util.annotation.RequiresViewModel

@RequiresView(R.layout.fragment_chats_nav)
@RequiresViewModel(ChatsNavViewModel::class)
class ChatsNavFragment : AbsFragment<ChatsNavViewModel, FragmentChatsNavBinding>() {
}