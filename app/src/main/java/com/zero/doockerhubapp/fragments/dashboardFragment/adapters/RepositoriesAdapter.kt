package com.zero.doockerhubapp.fragments.dashboardFragment.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.zero.doockerhubapp.databinding.LayoutRepoDescriptionBinding
import com.zero.doockerhubapp.fragments.dashboardFragment.model.Results

class RepositoriesAdapter(
    private val itemList: ArrayList<Results>,
    private val parentContext: Context
) :
    RecyclerView.Adapter<RepositoriesAdapter.RepoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val itemBinding =
            LayoutRepoDescriptionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repoItem: Results = itemList[position]
        holder.card.setOnClickListener {
            Toast.makeText(parentContext, "Clicked ${repoItem.name}", Toast.LENGTH_SHORT).show()
        }
        holder.bind(repoItem)

    }

    class RepoViewHolder(private val itemViewBinding: LayoutRepoDescriptionBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root) {
        val card = itemViewBinding.imageCv
        fun bind(repoItem: Results) {
            itemViewBinding.repoNameTv.text = repoItem.name
            itemViewBinding.starCountTv.text = repoItem.starCount.toString()
            itemViewBinding.repoTypeTv.text = repoItem.repositoryType.toString()
        }

    }
}


