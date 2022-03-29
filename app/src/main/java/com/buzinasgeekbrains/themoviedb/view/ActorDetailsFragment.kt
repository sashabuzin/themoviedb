package com.buzinasgeekbrains.themoviedb.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.buzinasgeekbrains.themoviedb.BuildConfig
import com.buzinasgeekbrains.themoviedb.viewmodel.ActorDetailsViewModel
import com.buzinasgeekbrains.themoviedb.databinding.ActorDetailsFragmentBinding
import com.buzinasgeekbrains.themoviedb.model.Actor

class ActorDetailsFragment : Fragment() {

    companion object {
        const val BUNDLE_EXTRA = "actor"
        fun newInstance(bundle: Bundle): ActorDetailsFragment {
            return ActorDetailsFragment().also { it.arguments = bundle }
        }
    }

    private var _binding: ActorDetailsFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ActorDetailsViewModel by lazy {
        ViewModelProvider(this).get(ActorDetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ActorDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.filmedInList.layoutManager = LinearLayoutManager(requireActivity())
        arguments?.getParcelable<Actor>(BUNDLE_EXTRA)?.let {
            binding.actorNameMain.text = it.name
            binding.actorBiography.text = it.biography
            binding.placeOfBirthList.text = it.placeOfBirth
            binding.birthdayList.text = it.birthday
            binding.genderList.text = if (it.gender == 1) "Male" else "Female"
            binding.popularityList.text = it.popularity.toString()
        }
    }

//    private fun displayActorInfo(actorDTO: ActorDTO) {
//
//    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}