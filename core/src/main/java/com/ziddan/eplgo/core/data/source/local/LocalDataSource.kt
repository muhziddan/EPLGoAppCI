package com.ziddan.eplgo.core.data.source.local

import com.ziddan.eplgo.core.data.source.local.entity.ClubEntity
import com.ziddan.eplgo.core.data.source.local.room.ClubDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val clubDao: ClubDao) {

    fun getAllClub(): Flow<List<ClubEntity>> = clubDao.getAllClub()

    fun getFavoriteClub(): Flow<List<ClubEntity>> = clubDao.getFavoriteClub()

    suspend fun insertClub(clubList: List<ClubEntity>) = clubDao.insertClub(clubList)

    fun setFavoriteClub(club: ClubEntity, newState: Boolean) {
        club.isFavorite = newState
        clubDao.updateFavoriteClub(club)
    }
}