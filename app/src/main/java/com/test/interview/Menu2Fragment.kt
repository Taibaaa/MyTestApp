package com.test.interview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.interview.adapters.TestingAdapter
import com.test.interview.databinding.FragmentHomeBinding
import com.test.interview.databinding.FragmentMenu2Binding
import com.test.interview.viewmodel.DataViewModel

class Menu2Fragment : Fragment() {

    private lateinit var binding: FragmentMenu2Binding
    private lateinit var viewModel: DataViewModel
    private lateinit var adapter: TestingAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenu2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvItem.layoutManager = LinearLayoutManager(requireContext())
        adapter = TestingAdapter(emptyList())
        binding.rvItem.adapter = adapter

        // set up ViewModel
        viewModel = ViewModelProvider(this)[DataViewModel::class.java]
        viewModel.getItems().observe(requireActivity()) { items ->
            adapter.updateItems(items)
        }

        // set up RecyclerView pagination
        binding.rvItem.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                val totalItemCount = layoutManager.itemCount
                if (lastVisibleItemPosition == totalItemCount - 1) {
                    viewModel.loadMoreItems()
                    Toast.makeText(
                        requireActivity(),
                        "50 more items added to the list",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }
}
