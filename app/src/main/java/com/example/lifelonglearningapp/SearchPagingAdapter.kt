package com.example.lifelonglearningapp

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.paging.LoadState
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.lifelonglearningapp.databinding.ItemSearchBinding
import com.google.android.gms.common.util.WorkSourceUtil.size
import timber.log.Timber
import java.nio.file.DirectoryStream
import java.nio.file.Files.size


class SearchPagingAdapter(private val onSelect: (Items?) -> Unit) :
    PagingDataAdapter<Items, SearchPagingAdapter.SearchViewHolder>(COMPARATOR) {



    inner class SearchViewHolder(private val binding: ItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bindAdapter(item: Items) = with(binding) {
            searchTitle.text = item.lctreNm
            searchDay.text = item.edcStartDay + " ~ " + item.edcEndDay
            searchDate.text = item.operDay
        }

    }


    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val item = getItem(position) ?: return
        item?.let { holder.bindAdapter(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            ItemSearchBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    }




    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Items>() {
            override fun areItemsTheSame(oldItem: Items, newItem: Items): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

            override fun areContentsTheSame(oldItem: Items, newItem: Items): Boolean {
                return oldItem == newItem
            }
        }
    }


}