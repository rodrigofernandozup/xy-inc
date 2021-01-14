package com.xyinc.gps.model

import lombok.Builder
import lombok.Data
import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "poi")
@Data
@Builder
class Poi (

    @Id
    @Column(name = "name", nullable = false, updatable = false)
    var name: String = "",

    @Column(name = "x_coordinates", nullable = false)
    var xCoordinates: Long = 0,

    @Column(name = "y_coordinates", nullable = false)
    var yCoordinates: Long = 0
) : Serializable