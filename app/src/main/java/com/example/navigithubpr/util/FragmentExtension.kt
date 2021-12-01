package com.example.navigithubpr.util

import androidx.fragment.app.Fragment
import com.example.navigithubpr.PrApplication
import com.example.navigithubpr.ViewModelFactory


fun Fragment.getViewModelFactory(): ViewModelFactory {
    val repository = (requireContext().applicationContext as PrApplication).prRepository
    return ViewModelFactory(repository, this)
}