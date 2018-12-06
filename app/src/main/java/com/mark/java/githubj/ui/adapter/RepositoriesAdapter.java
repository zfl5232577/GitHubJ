package com.mark.java.githubj.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mark.java.githubj.R;
import com.mark.java.githubj.data.ReceivedEvent;
import com.mark.java.githubj.data.Repos;
import com.mark.java.githubj.databinding.ItemHomeFragmentBinding;
import com.mark.java.githubj.databinding.ItemReposFragmentBinding;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

/**
 * <pre>
 *     author : Mark
 *     e-mail : makun.cai@aorise.org
 *     time   : 2018/12/06
 *     desc   : TODO
 *     version: 1.0
 * </pre>
 */
public class RepositoriesAdapter extends ListAdapter<Repos, RepositoriesAdapter.ReposViewHolder> {

    public RepositoriesAdapter() {
        super(new DiffUtil.ItemCallback<Repos>() {
            @Override
            public boolean areItemsTheSame(Repos oldItem, Repos newItem) {
                return false;
            }

            @Override
            public boolean areContentsTheSame(Repos oldItem, Repos newItem) {
                return false;
            }
        });
    }

    @NonNull
    @Override
    public RepositoriesAdapter.ReposViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ReposViewHolder(DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_repos_fragment, parent,
                false));
    }

    @Override
    public void onBindViewHolder(@NonNull RepositoriesAdapter.ReposViewHolder holder, int position) {
        holder.getBinding().setData(getItem(position));
    }

    static class ReposViewHolder extends RecyclerView.ViewHolder {

        private ItemReposFragmentBinding mBinding;

        public ReposViewHolder(ItemReposFragmentBinding itemReposFragmentBinding) {
            super(itemReposFragmentBinding.getRoot());
            mBinding = itemReposFragmentBinding;
        }


        public ItemReposFragmentBinding getBinding() {
            return mBinding;
        }
    }
}
