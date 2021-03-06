package com.buzinasgeekbrains.themoviedb.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import coil.api.load
import com.buzinasgeekbrains.themoviedb.viewmodel.ActorDetailsViewModel
import com.buzinasgeekbrains.themoviedb.databinding.ActorDetailsFragmentBinding
import com.buzinasgeekbrains.themoviedb.model.Actor
import com.buzinasgeekbrains.themoviedb.model.ActorDTO
import com.buzinasgeekbrains.themoviedb.model.ActorDetailsDTO
import com.buzinasgeekbrains.themoviedb.model.ActorLoader
import com.buzinasgeekbrains.themoviedb.viewmodel.AppState

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
    ): View {
        _binding = ActorDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.filmedInList.layoutManager = LinearLayoutManager(requireActivity())
        binding.progressBarcv.visibility = View.VISIBLE
        arguments?.getParcelable<ActorDTO>(BUNDLE_EXTRA)?.let {


            ActorLoader.load(it, object: ActorLoader.OnActorLoadListener {
                override fun onLoaded(actorDetailsDTO: ActorDetailsDTO) {
                        binding.actorNameMain.text = actorDetailsDTO.name
                        binding.actorBiographyText.text = actorDetailsDTO.biography
                        binding.placeOfBirthList.append(" ${actorDetailsDTO.place_of_birth}")
                        binding.birthdayList.append(" ${actorDetailsDTO.birthday}")
                        binding.genderList.append(" ${if (actorDetailsDTO.gender == 1) " Male" else " Female"}")
                        binding.popularityList.append(" ${actorDetailsDTO.popularity.toString()}")
                        binding.actorImageView.load("https://image.tmdb.org/t/p/w342${actorDetailsDTO.profile_path}")
                        binding.progressBarcv.visibility = View.GONE

                }

                override fun onFailed(throwable: Throwable) {
                    Toast.makeText(requireActivity(), throwable.message, Toast.LENGTH_SHORT).show()
                }
            })

        }
    }

//    private fun render(appState: AppState) {
//
//    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}