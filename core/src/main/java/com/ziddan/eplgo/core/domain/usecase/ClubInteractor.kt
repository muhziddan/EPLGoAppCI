package com.ziddan.eplgo.core.domain.usecase

import com.ziddan.eplgo.core.domain.model.Club
import com.ziddan.eplgo.core.domain.repository.IClubRepository

class ClubInteractor(private val clubRepository: IClubRepository) : ClubUseCase {

    override fun getAllClub() = clubRepository.getAllClub()

    override fun getFavoriteClub() = clubRepository.getFavoriteClub()

    override fun setFavoriteClub(club: Club, state: Boolean) =
        clubRepository.setFavoriteClub(club, state)
}