package com.mark.java.githubj.utils;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.mark.java.githubj.R;

import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import cn.aorise.common.core.module.glide.GlideApp;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.ColorFilterTransformation;

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

    @BindingAdapter("bind_adapter")
    public static void bindAdapter(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        if (recyclerView != null) {
            recyclerView.setAdapter(adapter);
        }
    }

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

    @BindingAdapter("bind_imageUrl_blur")
    public static void bindImageBlur(ImageView imageView, String imageUrl) {
        if (!TextUtils.isEmpty(imageUrl)) {
            GlideApp.with(imageView.getContext())
                    .load(imageUrl)
                    .placeholder(R.color.colorPrimary)
                    .apply(new RequestOptions().transforms(new BlurTransformation(25),
                            new ColorFilterTransformation(getColorWithAlpha(0.6f, ContextCompat.getColor(imageView.getContext(), R.color.colorPrimary)))))
                    .into(imageView);
        }
    }


    @BindingAdapter("bind_visibility")
    public static void setVisible(View view, boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    /**
     * 对rgb色彩加入透明度
     *
     * @param alpha     透明度，取值范围 0.0f -- 1.0f.
     * @param baseColor
     * @return a color with alpha made from base color
     */
    public static int getColorWithAlpha(float alpha, int baseColor) {
        int a = Math.min(255, Math.max(0, (int) (alpha * 255))) << 24;
        int rgb = 0x00ffffff & baseColor;
        return a + rgb;
    }
}
