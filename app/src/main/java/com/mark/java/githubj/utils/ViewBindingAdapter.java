package com.mark.java.githubj.utils;

import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import androidx.databinding.BindingAdapter;

/**
 * <pre>
 *     author : Mark
 *     e-mail : makun.cai@aorise.org
 *     time   : 2018/12/05
 *     desc   : TODO
 *     version: 1.0
 * </pre>
 */
public class ViewBindingAdapter {

    @BindingAdapter({"imageFromUrl"})
    public static void bindImageFromUrl(ImageView imageView,String imageUrl){
        if (!TextUtils.isEmpty(imageUrl)) {
            Glide.with(imageView.getContext())
                    .load(imageUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView);
        }
    }

}
