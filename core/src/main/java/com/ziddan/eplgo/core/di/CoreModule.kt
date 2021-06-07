package com.ziddan.eplgo.core.di

import androidx.room.Room
import com.ziddan.eplgo.core.data.ClubRepository
import com.ziddan.eplgo.core.data.source.local.LocalDataSource
import com.ziddan.eplgo.core.data.source.local.room.ClubDatabase
import com.ziddan.eplgo.core.data.source.remote.RemoteDataSource
import com.ziddan.eplgo.core.data.source.remote.network.ApiService
import com.ziddan.eplgo.core.domain.repository.IClubRepository
import com.ziddan.eplgo.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<ClubDatabase>().clubDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            ClubDatabase::class.java, "Tourism.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.thesportsdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IClubRepository> {
        ClubRepository(
            get(),
            get(),
            get()
        )
    }
}