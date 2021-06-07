package com.ziddan.eplgo.core.data.source.remote.network

import com.ziddan.eplgo.core.data.source.remote.response.ListClubResponse
import retrofit2.http.GET

interface ApiService {
    @GET("api/v1/json/1/search_all_teams.php?l=English%20Premier%20League")
    suspend fun getList(): ListClubResponse
}
