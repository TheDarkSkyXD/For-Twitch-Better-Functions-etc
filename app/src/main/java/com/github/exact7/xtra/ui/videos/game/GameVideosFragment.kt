package com.github.exact7.xtra.ui.videos.game

import androidx.lifecycle.Observer
import com.github.exact7.xtra.R
import com.github.exact7.xtra.model.kraken.video.Period
import com.github.exact7.xtra.model.kraken.video.Sort
import com.github.exact7.xtra.ui.videos.BaseVideosFragment
import com.github.exact7.xtra.util.C
import kotlinx.android.synthetic.main.fragment_videos.*

class GameVideosFragment : BaseVideosFragment(), GameVideosSortDialog.OnFilter {

    override lateinit var viewModel: GameVideosViewModel

    override fun initialize() {
        viewModel = createViewModel()
        binding.viewModel = viewModel
        binding.sortText = viewModel.sortText
        viewModel.list.observe(this, Observer {
            adapter.submitList(it)
        })
        viewModel.setGame(requireArguments().getParcelable(C.GAME)!!)
        sortBar.setOnClickListener { GameVideosSortDialog.newInstance(viewModel.sort, viewModel.period).show(childFragmentManager, null) }
    }

    override fun onChange(sort: Sort, sortText: CharSequence, period: Period, periodText: CharSequence) {
        adapter.submitList(null)
        viewModel.filter(sort, period, getString(R.string.sort_and_period, sortText, periodText))
    }
}
