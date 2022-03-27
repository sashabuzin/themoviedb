package com.buzinasgeekbrains.themoviedb.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.buzinasgeekbrains.themoviedb.R
import com.buzinasgeekbrains.themoviedb.databinding.ActorsFragmentBinding
import com.buzinasgeekbrains.themoviedb.databinding.MainFragmentBinding
import com.buzinasgeekbrains.themoviedb.model.Actor
import com.buzinasgeekbrains.themoviedb.viewmodel.ActorsViewModel
import com.buzinasgeekbrains.themoviedb.viewmodel.AppState
import kotlinx.android.synthetic.main.actors_fragment.*

class ActorsFragment : Fragment() {

    companion object {
        fun newInstance() = ActorsFragment()
    }

    private var _binding: ActorsFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ActorsViewModel

    private val adapter = ActorsFragmentAdapter { actor ->
        val manager = activity?.supportFragmentManager
        if (manager != null) {
            val bundle = Bundle()
            bundle.putParcelable(ActorDetailsFragment.BUNDLE_EXTRA, actor)
            manager.beginTransaction()
                .add(R.id.container_main, ActorDetailsFragment.newInstance(bundle))
                .addToBackStack("")
                .commitAllowingStateLoss()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ActorsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            binding.actorsRecyclerView.adapter = adapter
            binding.actorsRecyclerView.layoutManager = GridLayoutManager(requireActivity(), 2)

        viewModel = ViewModelProvider(this).get(ActorsViewModel::class.java)
        viewModel.getData().observe(viewLifecycleOwner, Observer {
            render(it)
        })
        viewModel.getActorFromLocalStorage()
    }

    private fun render(state: AppState) {
        when (state) {
            is AppState.Success<*> -> {
                binding.progressBarcv.visibility = View.GONE
                adapter.setActor(state.data as List<*>)
            }
            is AppState.Error -> {
                binding.progressBarcv.visibility = View.VISIBLE
                viewModel.getActorFromLocalStorage()
            }
            is AppState.Loading -> {
                binding.progressBarcv.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter.removeListener()
        _binding = null
    }

}