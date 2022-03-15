package com.buzinasgeekbrains.themoviedb.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.buzinasgeekbrains.themoviedb.viewmodel.ActorDetailsViewModel
import com.buzinasgeekbrains.themoviedb.R
import com.buzinasgeekbrains.themoviedb.databinding.ActorDetailsFragmentBinding
import com.buzinasgeekbrains.themoviedb.databinding.FilmDetailsFragmentBinding

class ActorDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = ActorDetailsFragment()
    }

    private var _binding: ActorDetailsFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ActorDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ActorDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ActorDetailsViewModel::class.java)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}