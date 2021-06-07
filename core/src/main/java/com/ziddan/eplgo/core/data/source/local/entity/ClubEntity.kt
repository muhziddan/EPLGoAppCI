package com.ziddan.eplgo.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "club")
data class ClubEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "idTeam")
    var idTeam: String,

    @ColumnInfo(name = "strTeam")
    var strTeam: String,

    @ColumnInfo(name = "strDescriptionEN")
    var strDescriptionEN: String,

    @ColumnInfo(name = "strStadiumThumb")
    var strStadiumThumb: String,

    @ColumnInfo(name = "strStadiumLocation")
    var strStadiumLocation: String,

    @ColumnInfo(name = "strTeamBadge")
    var strTeamBadge: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)
