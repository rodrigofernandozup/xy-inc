package com.xyinc.poi.entity

import com.xyinc.poi.controller.response.PoiResponse
import com.xyinc.poi.domain.Poi
import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "poi")
class PoiEntity (

    @Id
    @Column(name = "name", nullable = false, updatable = false)
    var name: String = "",

    @Column(name = "x_coordinate", nullable = false)
    var xCoordinate: Long = 0,

    @Column(name = "y_coordinate", nullable = false)
    var yCoordinate: Long = 0

) : Serializable

fun PoiEntity.toPoi() = Poi(
    name = name,
    xCoordinate = xCoordinate,
    yCoordinate = yCoordinate
)
