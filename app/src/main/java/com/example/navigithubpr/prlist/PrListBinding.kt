package com.example.navigithubpr.prlist

import android.net.Uri
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.navigithubpr.data.response.GithubIssuesResponse


/**
 * [BindingAdapter]s for the [Task]s list.
 */

@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: List<GithubIssuesResponse>?) {
    items?.let {
        (listView.adapter as PrAdapter).submitList(items)
    }
}


@BindingAdapter("android:imageResource")
fun setImageViewResource(imageView: ImageView, resource: String?) {
    Glide.with(imageView).load(resource).into(imageView)
}