package com.example.nasa.framework.di

import com.example.core.data.PlanetaryRemoteSource
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    fun providesRetrofit(gsonConverterFactory: GsonConverterFactory,
                         rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
                         okHttpClient: OkHttpClient
    ) : Retrofit {
         return Retrofit.Builder().baseUrl("https://api.nasa.gov/planetary/apod")
             .addConverterFactory(gsonConverterFactory)
             .addCallAdapterFactory(rxJava2CallAdapterFactory)
             .client(okHttpClient)
             .build()
    }

    @Provides
    fun providePlanetaryService(retrofit: Retrofit) : PlanetaryRemoteSource
    {
        return retrofit.create(PlanetaryRemoteSource::class.java)
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(/*cache: Cache*/): OkHttpClient {
        val client = OkHttpClient.Builder()
           // .cache(cache)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)


        return client.build()
    }

   /* @Provides
    @Singleton
    fun providesOkhttpCache(context: Context): Cache {
        val cacheSize = 10 * 1024 * 1024 // 10 MB
        return Cache(context.cacheDir, cacheSize.toLong())
    }*/

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun providesGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun providesRxJavaCallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

}