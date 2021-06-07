package com.ziddan.eplgo.di

import com.ziddan.eplgo.core.domain.usecase.ClubInteractor
import com.ziddan.eplgo.core.domain.usecase.ClubUseCase
import com.ziddan.eplgo.detail.DetailClubViewModel
import com.ziddan.eplgo.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<ClubUseCase> { ClubInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailClubViewModel(get()) }
}