package com.ziddan.eplgo.detail

import androidx.lifecycle.ViewModel
import com.ziddan.eplgo.core.domain.model.Club
import com.ziddan.eplgo.core.domain.usecase.ClubUseCase

class DetailClubViewModel(private val clubUseCase: ClubUseCase) : ViewModel() {
    fun setFavoriteClub(club: Club, newStatus: Boolean) =
        clubUseCase.setFavoriteClub(club, newStatus)
}