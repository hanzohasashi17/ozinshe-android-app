package com.example.ozinshe

import android.app.Application
import android.util.Log
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.request.CachePolicy
import coil.util.DebugLogger
import dagger.hilt.android.HiltAndroidApp
import okhttp3.internal.cacheGet

@HiltAndroidApp
class App: Application(), ImageLoaderFactory {
    override fun newImageLoader(): ImageLoader {
        return ImageLoader(this).newBuilder()
            .diskCachePolicy(CachePolicy.ENABLED)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .crossfade(true)
            .logger(DebugLogger())
            .build()
    }
}