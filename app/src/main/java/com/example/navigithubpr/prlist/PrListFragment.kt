package com.example.navigithubpr.prlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.navigithubpr.R
import com.example.navigithubpr.data.UserInput
import com.example.navigithubpr.databinding.FragmentPrListBinding
import com.example.navigithubpr.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class PrListFragment : Fragment() {

    private val viewModel: PrViewModel by activityViewModels();

    private lateinit var viewDataBinding: FragmentPrListBinding

    private lateinit var listAdapter: PrAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_pr_list, container, false)
        viewDataBinding = FragmentPrListBinding.bind(root).apply {
            viewmodel = viewModel
        }
        // Set the lifecycle owner to the lifecycle of the view
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userInput:UserInput = requireArguments().getSerializable("userInput") as UserInput
        setupListAdapter(userInput)
    }
    private fun setupListAdapter(userInput:UserInput) {
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            listAdapter = PrAdapter(viewModel)
            viewDataBinding.recyclerView.adapter = listAdapter
        } else {
            Timber.w("ViewModel not initialized when attempting to set up adapter.")
        }
        viewModel!!.refreshTask(userInput)
    }

}