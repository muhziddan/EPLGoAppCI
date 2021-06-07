package com.ziddan.eplgo.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListClubResponse(

    @field:SerializedName("teams")
    val teams: List<ClubResponse>
)