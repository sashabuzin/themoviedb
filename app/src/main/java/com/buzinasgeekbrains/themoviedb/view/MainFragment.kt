package com.buzinasgeekbrains.themoviedb.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.buzinasgeekbrains.themoviedb.viewmodel.AppState
import com.buzinasgeekbrains.themoviedb.viewmodel.MainViewModel
import com.buzinasgeekbrains.themoviedb.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nowPlayingRecyclerView.apply {
//            this.adapter = MainFragmentAdapter()
            this.layoutManager = LinearLayoutManager(requireActivity())
        }
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getData().observe(viewLifecycleOwner, Observer {
//            render(it)
        })

    }

    private fun render(state: Any) {
        when (state) {
            is AppState.Success<*> -> {
                binding.progressBarcv.visibility = View.GONE
            }
            is AppState.Error -> {
                //TODO reload
                binding.progressBarcv.visibility = View.VISIBLE

            }
            is AppState.Loading -> {
                binding.progressBarcv.visibility = View.VISIBLE

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}