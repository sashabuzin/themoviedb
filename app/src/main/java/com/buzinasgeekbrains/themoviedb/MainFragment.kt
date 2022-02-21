package com.buzinasgeekbrains.themoviedb

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.buzinasgeekbrains.themoviedb.Repositories.AppState
import com.buzinasgeekbrains.themoviedb.ViewModels.MainViewModel
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
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

//        parentFragmentManager.beginTransaction().replace(R.id.container_main)
//        render(state)
    }

    private fun render(state: Any) {
        when (state) {
            is AppState.Success -> {
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