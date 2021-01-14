package com.xyinc.gps.controller.model.request

import com.xyinc.gps.model.Poi
import lombok.Builder
import lombok.Data
import javax.validation.constraints.NotBlank
import javax.validation.constraints.PositiveOrZero

@Builder
@Data
class PoiRequest {

    @NotBlank
    var name: String = ""

    @PositiveOrZero
    var xCoordinate: Long = 0

    @PositiveOrZero
    var yCoordinate: Long = 0

    /*fun toPoiEntity(poeEntity: Poi): PoiRequest {
        return PoiRequest().apply {
            name = poeEntity.name; xCoordinate = poeEntity.xCoordinates; yCoordinate = poeEntity.yCoordinates;)
        }
    }*/
}