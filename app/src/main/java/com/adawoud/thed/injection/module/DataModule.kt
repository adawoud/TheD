package com.adawoud.thed.injection.module

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.room.Room
import com.adawoud.thed.data.local.ProductDatabase
import com.adawoud.thed.data.remote.TheDService
import com.adawoud.thed.injection.scope.ApplicationContext
import com.squareup.moshi.Moshi
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object DataModule {
    private const val diskCacheSize = 50000000 // 50 megabytes

    @Provides
    @Singleton
    @JvmStatic
    fun sharedPreferences(
        @ApplicationContext
        application: Application
    ): SharedPreferences =
        application.getSharedPreferences("com.adawoud.thed", MODE_PRIVATE)

    @Provides
    @Singleton
    @JvmStatic
    fun moshi(): Moshi = Moshi.Builder().build()

    @Provides
    @Singleton
    @JvmStatic
    fun httpCache(
        @ApplicationContext
        application: Application
    ): Cache =
        Cache(application.cacheDir, diskCacheSize.toLong())

    @Provides
    @Singleton
    @JvmStatic
    fun okHttpClient(cache: Cache): OkHttpClient = OkHttpClient.Builder()
        .cache(cache)
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(1, TimeUnit.MINUTES)
        .build()

    @Provides
    @Singleton
    @JvmStatic
    fun retrofit(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl("https://limitless-forest-98976.herokuapp.com/")
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    @JvmStatic
    fun picasso(): Picasso = Picasso.get()

    @Provides
    @Singleton
    @JvmStatic
    fun theDService(retrofit: Retrofit): TheDService =
        retrofit.create<TheDService>(TheDService::class.java)

    @Provides
    @Singleton
    @JvmStatic
    fun database(@ApplicationContext application: Application): ProductDatabase =
        Room.databaseBuilder(application, ProductDatabase::class.java, "thed.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    @JvmStatic
    fun productDao(database: ProductDatabase) = database.productDao()

    @Provides
    @Singleton
    @JvmStatic
    fun productDetailDao(database: ProductDatabase) = database.productDetailDao()

}