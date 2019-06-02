package app.anysa.ui.modules.main

import app.anysa.R
import app.anysa.databinding.FragmentMainBinding
import app.anysa.ui.base.abs.AbsFragment
import app.anysa.util.annotation.RequiresView
import app.anysa.util.annotation.RequiresViewModel

@RequiresView(R.layout.fragment_main)
@RequiresViewModel(MainViewModel::class)
class MainFragment : AbsFragment<MainViewModel, FragmentMainBinding>() {

//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//
//        return inflater.inflate(R.layout.fragment_main, container, false)
//    }
}