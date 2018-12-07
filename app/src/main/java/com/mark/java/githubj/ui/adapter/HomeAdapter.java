package com.mark.java.githubj.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mark.java.githubj.R;
import com.mark.java.githubj.data.ReceivedEvent;
import com.mark.java.githubj.databinding.ItemHomeFragmentBinding;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
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
public class HomeAdapter extends PagedListAdapter<ReceivedEvent, HomeAdapter.HomeViewHolder> {

    public HomeAdapter() {
        super(new DiffUtil.ItemCallback<ReceivedEvent>() {
            @Override
            public boolean areItemsTheSame(ReceivedEvent oldItem, ReceivedEvent newItem) {
                return oldItem.getId().equals(newItem.getId());
            }

            @Override
            public boolean areContentsTheSame(ReceivedEvent oldItem, ReceivedEvent newItem) {
                return oldItem.getId().equals(oldItem.getId());
            }
        });
    }

    @NonNull
    @Override
    public HomeAdapter.HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeViewHolder(DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_home_fragment, parent,
                false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.HomeViewHolder holder, int position) {
        holder.getBinding().setData(getItem(position));
    }

    static class HomeViewHolder extends RecyclerView.ViewHolder {

        private ItemHomeFragmentBinding mBinding;

        public HomeViewHolder(ItemHomeFragmentBinding itemHomeFragmentBinding) {
            super(itemHomeFragmentBinding.getRoot());
            mBinding = itemHomeFragmentBinding;
        }


        public ItemHomeFragmentBinding getBinding() {
            return mBinding;
        }
    }
}
