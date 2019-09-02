package com.example.nasa.presentation.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.core.domain.PlanetaryResponse
import com.example.core.networking.Outcome
import com.example.nasa.R
import com.example.nasa.framework.PostDH
import com.example.nasa.presentation.list.ListViewModel
import com.example.nasa.presentation.list.ListViewModelFactory
import kotlinx.android.synthetic.main.fragment_details.*
import javax.inject.Inject


class DetailsFragment : Fragment()
{

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this).load(arguments?.getString("image")).into(image)
         title.text = arguments?.getString("title")
        explanation.text = arguments?.getString("explanation")
        version.text = "Version : "+arguments?.getString("version")
    }


}