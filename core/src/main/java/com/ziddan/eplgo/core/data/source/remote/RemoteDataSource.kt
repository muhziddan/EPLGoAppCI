package com.ziddan.eplgo.core.data.source.remote

import android.util.Log
import com.ziddan.eplgo.core.data.source.remote.network.ApiResponse
import com.ziddan.eplgo.core.data.source.remote.network.ApiService
import com.ziddan.eplgo.core.data.source.remote.response.ClubResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllClub(): Flow<ApiResponse<List<ClubResponse>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getList()
                val dataArray = response.teams
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.teams))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}

