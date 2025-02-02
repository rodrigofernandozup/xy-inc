package com.xyinc.poi.controller.request

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.xyinc.poi.domain.Poi
import com.xyinc.poi.entity.PoiEntity

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class PoiRequest (
    val name: String,
    val x: Long,
    val y: Long
)

fun PoiRequest.toPoi() = Poi(
    name = name,
    xCoordinate = x,
    yCoordinate = y
)