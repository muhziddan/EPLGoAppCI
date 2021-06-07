package com.ziddan.eplgo.core.domain.repository

import com.ziddan.eplgo.core.data.Resource
import com.ziddan.eplgo.core.domain.model.Club
import kotlinx.coroutines.flow.Flow

interface IClubRepository {

    fun getAllClub(): Flow<Resource<List<Club>>>

    fun getFavoriteClub(): Flow<List<Club>>

    fun setFavoriteClub(club: Club, state: Boolean)

}