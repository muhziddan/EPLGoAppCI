package com.ziddan.eplgo.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ziddan.eplgo.core.domain.usecase.ClubUseCase

class HomeViewModel(clubUseCase: ClubUseCase) : ViewModel() {
    val club = clubUseCase.getAllClub().asLiveData()
}