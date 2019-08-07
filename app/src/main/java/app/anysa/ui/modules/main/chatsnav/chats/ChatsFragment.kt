package app.anysa.ui.modules.main.chatsnav.chats

import app.anysa.R
import app.anysa.databinding.FragmentChatsBinding
import app.anysa.ui.base.abs.AbsFragment
import app.anysa.util.annotation.RequiresView
import app.anysa.util.annotation.RequiresViewModel

@RequiresView(R.layout.fragment_chats)
@RequiresViewModel(ChatsViewModel::class)
class ChatsFragment : AbsFragment<ChatsViewModel, FragmentChatsBinding>() {
}