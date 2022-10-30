package com.example.navigithubpr.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.navigithubpr.R
import com.example.navigithubpr.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by activityViewModels();

    private lateinit var viewDataBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentHomeBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Set the lifecycle owner to the lifecycle of the view
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        setupNavigation()
    }

    private fun setupNavigation(){
        viewModel.dataSubmitted.observe(viewLifecycleOwner, Observer { userInput ->
            if(userInput.folderName.isNullOrEmpty() || userInput.orgname.isNullOrEmpty() || userInput.prStatus.isNullOrEmpty()){
                Toast.makeText(activity, "Invalid Input", Toast.LENGTH_LONG).show()
                return@Observer
            }
            findNavController().navigate(R.id.action_homeFragment_to_prListFragment, Bundle().apply {
                putSerializable("userInput", userInput)
            })
        })
    }


}