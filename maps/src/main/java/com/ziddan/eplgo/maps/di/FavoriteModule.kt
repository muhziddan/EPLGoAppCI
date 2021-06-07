package com.ziddan.eplgo.maps.di

import com.ziddan.eplgo.maps.FavoriteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteViewModel(get()) }
}