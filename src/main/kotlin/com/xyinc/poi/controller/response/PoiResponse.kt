package com.xyinc.poi.controller.response

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class PoiResponse (
    val name: String,
    val x: Long,
    val y: Long
)