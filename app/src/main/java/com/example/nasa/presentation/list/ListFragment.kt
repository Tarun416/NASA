package com.example.nasa.presentation.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.domain.PlanetaryResponse
import com.example.core.networking.Outcome
import com.example.nasa.R
import com.example.nasa.framework.PostDH
import com.example.nasa.presentation.detail.DetailsFragment
import kotlinx.android.synthetic.main.fragment_list.*
import java.io.IOException
import javax.inject.Inject

class ListFragment : Fragment() ,ListAdapter.OnPicClick {



    private val TAG: String = ListFragment.javaClass.name
    private lateinit var adapter: ListAdapter


    private val component by lazy { PostDH.listComponent() }
    private lateinit var planetaryResponse: PlanetaryResponse

    @Inject
    lateinit var viewModelFactory: ListViewModelFactory

    private val viewModel: ListViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(ListViewModel::class.java)
    }


    companion object {
        fun newInstance() = ListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        component.inject(this)

        adapter = ListAdapter(activity!!,this)
        picturerv.layoutManager = LinearLayoutManager(activity!!)
        picturerv.adapter = adapter
        viewModel.fetchPictures()
        initiateDataListener()

    }

    private fun initiateDataListener() {
        //Observe the outcome and update state of the screen  accordingly
        viewModel.postsOutcome.observe(
            this,
            androidx.lifecycle.Observer<Outcome<PlanetaryResponse>> { outcome ->
                Log.d(TAG, "initiateDataListener: $outcome")
                when (outcome) {

                    is Outcome.Progress -> {
                        when {
                            outcome.loading -> progrssbar.visibility = View.VISIBLE
                            else -> progrssbar.visibility = View.GONE
                        }

                    }

                    is Outcome.Success -> {
                        Log.d(TAG, "initiateDataListener: Successfully loaded data")
                        planetaryResponse = outcome.data
                        adapter.update(planetaryResponse)
                    }

                    is Outcome.Failure -> {

                        if (outcome.e is IOException)
                            Toast.makeText(
                                context,
                                "No internet",
                                Toast.LENGTH_LONG
                            ).show()
                        else
                            Toast.makeText(
                                context,
                                "Failed, try again",
                                Toast.LENGTH_LONG
                            ).show()
                    }

                }
            })

    }

    override fun onClick(pos: Int) {
        val detailsFragment = DetailsFragment()
        val args = Bundle()
        args.putString("title",planetaryResponse.title)
        args.putString("explanation",planetaryResponse.explanation)
        args.putString("image",planetaryResponse.hdurl)
        args.putString("version",planetaryResponse.serviceVersion)
        detailsFragment.arguments = args
        activity!!.supportFragmentManager.beginTransaction()
            .replace(R.id.content, detailsFragment)
            .commit()
    }


}