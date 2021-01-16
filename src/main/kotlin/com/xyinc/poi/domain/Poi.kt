package com.xyinc.poi.domain

import com.xyinc.poi.controller.request.PoiRequest
import com.xyinc.poi.controller.response.PoiResponse
import com.xyinc.poi.entity.PoiEntity

data class Poi (
    val name: String,
    val xCoordinate: Long,
    val yCoordinate: Long
)

fun Poi.toPoiEntity() = PoiEntity(
    name = name,
    xCoordinate = xCoordinate,
    yCoordinate = yCoordinate
)

fun Poi.toPoiResponse() = PoiResponse(
    name = name,
    x = xCoordinate,
    y = yCoordinate
)