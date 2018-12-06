package cn.aorise.common.core.module.glide;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.AppGlideModule;

import androidx.annotation.NonNull;

/**
 * <pre>
 *     author : admin
 *     e-mail : makun.cai@aorise.org
 *     time   : 2018/12/06
 *     desc   : TODO
 *     version: 1.0
 * </pre>
 */
@GlideModule
public class BaseGlideModule extends AppGlideModule {

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        super.registerComponents(context, glide, registry);
    }


    /**
     * Using the @GlideModule annotation requires a dependency on Glide’s annotations:
     */
    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context,
                diskCacheFolderName(context),
                diskCacheSizeBytes()))
                .setMemoryCache(new LruResourceCache(memoryCacheSizeBytes()));
    }

    /**
     * Implementations should return `false` after they and their dependencies have migrated
     * to Glide's annotation processor.
     */
    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }

    /**
     * set the memory cache size, unit is the [Byte].
     */
    private long memoryCacheSizeBytes() {
        return 1024 * 1024 * 20; // 20 MB
    }

    /**
     * set the disk cache size, unit is the [Byte].
     */
    private long diskCacheSizeBytes() {
        return 1024 * 1024 * 512; // 512 MB
    }

    /**
     * set the disk cache folder's name.
     */
    private String diskCacheFolderName(Context context){
        return "common_aorise";
    }
}
