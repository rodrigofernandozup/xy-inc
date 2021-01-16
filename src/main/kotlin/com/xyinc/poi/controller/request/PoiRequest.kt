package com.xyinc.poi.controller.request

import com.xyinc.poi.exceptionhandler.messages.ExceptionMessageEnum
import javax.validation.constraints.NotBlank
import javax.validation.constraints.PositiveOrZero

data class PoiRequest (

    @field:NotBlank(message = ExceptionMessageEnum.POI_NAME_IS_BLANK.message)
    val name: String,

    @field:PositiveOrZero
    val xCoordinate: Long,

    @field:PositiveOrZero
    val yCoordinate: Long

)