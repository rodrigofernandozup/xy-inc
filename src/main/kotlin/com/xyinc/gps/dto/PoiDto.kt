package com.xyinc.gps.dto

import lombok.Data
import javax.validation.constraints.NotBlank
import javax.validation.constraints.PositiveOrZero

@Data
class PoiDto {

    @NotBlank
    var name: String = ""

    @PositiveOrZero
    var xCoordinates: Long = 0

    @PositiveOrZero
    var yCoordinates: Long = 0
}