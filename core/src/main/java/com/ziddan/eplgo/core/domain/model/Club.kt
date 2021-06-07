package com.ziddan.eplgo.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Club(
    val idTeam: String,
    val strTeam: String,
    val strDescriptionEN: String,
    val strStadiumThumb: String,
    val strStadiumLocation: String,
    val strTeamBadge: String,
    val isFavorite: Boolean
) : Parcelable