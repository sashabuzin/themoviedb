package com.buzinasgeekbrains.themoviedb.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.buzinasgeekbrains.themoviedb.R
import com.buzinasgeekbrains.themoviedb.databinding.ActorsFragmentBinding
import com.buzinasgeekbrains.themoviedb.databinding.MainFragmentBinding
import com.buzinasgeekbrains.themoviedb.viewmodel.ActorsViewModel
import com.buzinasgeekbrains.themoviedb.viewmodel.AppState

class ActorsFragment : Fragment() {

    companion object {
        fun newInstance() = ActorsFragment()
    }

    private var _binding: ActorsFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ActorsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ActorsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ActorsViewModel::class.java)
        viewModel.getData().observe(viewLifecycleOwner, Observer {
            render(it)
        })
    }

    private fun render(state: Any) {
        when (state) {
            is AppState.Success<*> -> {
                //TODO gone loading
            }
            is AppState.Error -> {
                //TODO reload
            }
            is AppState.Loading -> {
                //TODO visible loading
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}