package com.buzinasgeekbrains.themoviedb

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.buzinasgeekbrains.themoviedb.Repositories.AppState
import com.buzinasgeekbrains.themoviedb.Repositories.Film
import com.buzinasgeekbrains.themoviedb.ViewModels.FilmDetailsViewModel
import com.buzinasgeekbrains.themoviedb.databinding.FilmDetailsFragmentBinding
import java.text.DateFormat
import java.util.*

class FilmDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = FilmDetailsFragment()
    }

    private var _binding: FilmDetailsFragmentBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: FilmDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FilmDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(FilmDetailsViewModel::class.java)
        viewModel.getData().observe(viewLifecycleOwner, Observer {
            render(it)
        })
        viewModel.getFilm()


    }

    private fun render(state: AppState) {
        when (state) {
            is AppState.Success -> {

                binding.progressBarcv.visibility = View.GONE
                binding.filmNameMain.text = state.film.name
                binding.ratingList.append(state.film.rating.toString())
                binding.budgetList.append(state.film.budget.toString())
                binding.revenueList.append(state.film.revenue.toString())
                binding.releaseDateList.append(state.film.date.toString())
                binding.filmDescription.text = state.film.description

            }
            is AppState.Error -> {
                viewModel.getFilm()
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