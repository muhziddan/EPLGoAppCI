package com.ziddan.eplgo.core.data

import com.ziddan.eplgo.core.data.source.local.LocalDataSource
import com.ziddan.eplgo.core.data.source.remote.RemoteDataSource
import com.ziddan.eplgo.core.data.source.remote.network.ApiResponse
import com.ziddan.eplgo.core.data.source.remote.response.ClubResponse
import com.ziddan.eplgo.core.domain.model.Club
import com.ziddan.eplgo.core.domain.repository.IClubRepository
import com.ziddan.eplgo.core.utils.AppExecutors
import com.ziddan.eplgo.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ClubRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IClubRepository {

    override fun getAllClub(): Flow<Resource<List<Club>>> =
        object : NetworkBoundResource<List<Club>, List<ClubResponse>>() {
            override fun loadFromDB(): Flow<List<Club>> {
                return localDataSource.getAllClub().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Club>?): Boolean =
                data == null || data.isEmpty()
            //true // ganti dengan true jika ingin selalu mengambil data dari internet

            override suspend fun createCall(): Flow<ApiResponse<List<ClubResponse>>> =
                remoteDataSource.getAllClub()

            override suspend fun saveCallResult(data: List<ClubResponse>) {
                val tourismList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertClub(tourismList)
            }
        }.asFlow()

    override fun getFavoriteClub(): Flow<List<Club>> {
        return localDataSource.getFavoriteClub().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteClub(club: Club, state: Boolean) {
        val clubEntity = DataMapper.mapDomainToEntity(club)
        appExecutors.diskIO().execute { localDataSource.setFavoriteClub(clubEntity, state) }
    }
}