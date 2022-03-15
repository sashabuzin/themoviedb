package com.buzinasgeekbrains.themoviedb.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.buzinasgeekbrains.themoviedb.viewmodel.AppState
import com.buzinasgeekbrains.themoviedb.viewmodel.FilmDetailsViewModel
import com.buzinasgeekbrains.themoviedb.databinding.FilmDetailsFragmentBinding
import com.buzinasgeekbrains.themoviedb.model.Film

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
        viewModel.getFilmFromLocalStorage()


    }

    private fun render(state: AppState) {
        when (state) {
            is AppState.Success<*> -> {

                val film = state.data as Film
                binding.progressBarcv.visibility = View.GONE
                binding.filmNameMain.text = film.name
                binding.ratingList.append(film.rating.toString())
                binding.budgetList.append(film.budget.toString())
                binding.revenueList.append(film.revenue.toString())
                binding.releaseDateList.append(film.date.toString())
                binding.filmDescription.text = film.description

            }
            is AppState.Error -> {
                viewModel.getFilmFromLocalStorage()
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