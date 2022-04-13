package com.chernybro.awesomenews.presentation.list_news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chernybro.awesomenews.databinding.FragmentListNewsBinding
import com.chernybro.awesomenews.domain.models.ArticlePreview
import com.chernybro.awesomenews.presentation.MainViewModel
import com.chernybro.awesomenews.utils.Constants
import timber.log.Timber


class NewsListFragment: Fragment() {
    private lateinit var binding: FragmentListNewsBinding
    private val newsAdapter: NewsAdapter = NewsAdapter()
    private lateinit var viewModel: MainViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureViewModel()
        configureRecyclerView()
        viewModel.getNewsList(Constants.DEFAULT_COUNTRY)
    }

    override fun onResume() {
        super.onResume()
    }

    private fun configureRecyclerView() {
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = newsAdapter
        val dividerItemDecoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        binding.recyclerView.addItemDecoration(dividerItemDecoration)
    }

    private fun configureViewModel() {
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        viewModel.itemsData.observe(viewLifecycleOwner) { list ->
            newsAdapter.setData(list)
        }
    }
}