package com.ziddan.eplgo.maps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ziddan.eplgo.core.domain.usecase.ClubUseCase

class FavoriteViewModel(clubUseCase: ClubUseCase) : ViewModel() {
    val favoriteClub = clubUseCase.getFavoriteClub().asLiveData()
}