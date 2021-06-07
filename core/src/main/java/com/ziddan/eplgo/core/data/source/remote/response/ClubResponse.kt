package com.ziddan.eplgo.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ClubResponse(
    @field:SerializedName("idTeam")
    val idTeam: String,

    @field:SerializedName("strTeam")
    val strTeam: String,

    @field:SerializedName("strDescriptionEN")
    val strDescriptionEN: String,

    @field:SerializedName("strStadiumThumb")
    val strStadiumThumb: String,

    @field:SerializedName("strStadiumLocation")
    val strStadiumLocation: String,

    @field:SerializedName("strTeamBadge")
    val strTeamBadge: String,
)