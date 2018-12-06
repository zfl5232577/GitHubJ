package com.mark.java.githubj.utils;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.mark.java.githubj.R;

import androidx.databinding.BindingAdapter;
import cn.aorise.common.core.module.glide.GlideApp;

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
    public static void bindImageFromUrl(ImageView imageView, String imageUrl) {
        if (!TextUtils.isEmpty(imageUrl)) {
            GlideApp.with(imageView.getContext())
                    .load(imageUrl)
                    .placeholder(new ColorDrawable(Color.parseColor("#55555555")))
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView);
        }
    }

    @BindingAdapter("bind_imageUrl_circle")
    public static void bindImageCircle(ImageView imageView, String imageUrl) {
        if (!TextUtils.isEmpty(imageUrl)) {
            GlideApp.with(imageView.getContext())
                    .load(imageUrl)
                    .placeholder(new ColorDrawable(Color.parseColor("#55555555")))
                    .apply(new RequestOptions().circleCrop())
                    .into(imageView);
        }
    }

    @BindingAdapter("bind_visibility")
    public static void setVisible(View view, boolean visible) {
        view.setVisibility(visible?View.VISIBLE:View.GONE);
    }

}
