package com.example.navigithubpr.prlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.navigithubpr.data.response.GithubIssuesResponse
import com.example.navigithubpr.databinding.PullItemBinding


class PrAdapter(private val viewModel: PrViewModel) :
    ListAdapter<GithubIssuesResponse, PrAdapter.ViewHolder>(TaskDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(viewModel, item)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: PullItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: PrViewModel, item: GithubIssuesResponse) {

            binding.viewmodel = viewModel
            binding.pr = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = PullItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}
//
/**
 * Callback for calculating the diff between two non-null items in a list.
 *
 * Used by ListAdapter to calculate the minimum number of changes between and old list and a new
 * list that's been passed to `submitList`.
 */
class TaskDiffCallback : DiffUtil.ItemCallback<GithubIssuesResponse>() {
    override fun areItemsTheSame(oldItem: GithubIssuesResponse, newItem: GithubIssuesResponse): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: GithubIssuesResponse, newItem: GithubIssuesResponse): Boolean {
        return oldItem == newItem
    }
}

