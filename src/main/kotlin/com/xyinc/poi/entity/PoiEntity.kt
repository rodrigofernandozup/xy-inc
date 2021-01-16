package com.xyinc.poi.entity

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

    @Column(name = "x_coordinates", nullable = false)
    var xCoordinates: Long = 0,

    @Column(name = "y_coordinates", nullable = false)
    var yCoordinates: Long = 0

) : Serializable
