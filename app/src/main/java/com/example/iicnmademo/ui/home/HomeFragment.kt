package com.example.iicnmademo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.iicnmademo.databinding.FragmentHomeBinding
import com.example.iicnmademo.domain.model.Record
import com.example.iicnmademo.ui.common.base.BaseFragment
import com.example.iicnmademo.ui.common.loadstate.ListLoadStateAdapter
import com.example.iicnmademo.utils.ViewLifecycleDelegate
import com.example.iicnmademo.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val homeViewModel: HomeViewModel by viewModels()

    private val homeAdapter by ViewLifecycleDelegate { HomeAdapter(::onMovieClicked) }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentHomeBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        listenOnMoviesPagedData()
    }

    private fun listenOnMoviesPagedData() = viewLifecycleOwner.lifecycleScope.launch {
        homeViewModel.movies.collectLatest { homeAdapter.submitData(it) }
    }

    private fun initUI() = with(binding) {
        postponeEnterTransition()
        recyclerView.apply {
            adapter = homeAdapter.withLoadStateFooter(
                footer = ListLoadStateAdapter { homeAdapter.retry() }
            )
            layoutManager = LinearLayoutManager(context)
            doOnPreDraw { startPostponedEnterTransition(); Timber.v("startPostponedEnterTransition called") }
        }
    }

    private fun onMovieClicked(
        record: Record,
    ) {
        showToast(record.title)
    }
}