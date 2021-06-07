package com.ziddan.eplgo.core.utils

import com.ziddan.eplgo.core.data.source.local.entity.ClubEntity
import com.ziddan.eplgo.core.data.source.remote.response.ClubResponse
import com.ziddan.eplgo.core.domain.model.Club

object DataMapper {
    fun mapResponsesToEntities(input: List<ClubResponse>): List<ClubEntity> {
        val clubList = ArrayList<ClubEntity>()
        input.map {
            val club = ClubEntity(
                idTeam = it.idTeam,
                strTeam = it.strTeam,
                strDescriptionEN = it.strDescriptionEN,
                strStadiumThumb = it.strStadiumThumb,
                strStadiumLocation = it.strStadiumLocation,
                strTeamBadge = it.strTeamBadge,
                isFavorite = false
            )
            clubList.add(club)
        }
        return clubList
    }

    fun mapEntitiesToDomain(input: List<ClubEntity>): List<Club> =
        input.map {
            Club(
                idTeam = it.idTeam,
                strTeam = it.strTeam,
                strDescriptionEN = it.strDescriptionEN,
                strStadiumThumb = it.strStadiumThumb,
                strStadiumLocation = it.strStadiumLocation,
                strTeamBadge = it.strTeamBadge,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Club) = ClubEntity(
        idTeam = input.idTeam,
        strTeam = input.strTeam,
        strDescriptionEN = input.strDescriptionEN,
        strStadiumThumb = input.strStadiumThumb,
        strStadiumLocation = input.strStadiumLocation,
        strTeamBadge = input.strTeamBadge,
        isFavorite = input.isFavorite
    )
}