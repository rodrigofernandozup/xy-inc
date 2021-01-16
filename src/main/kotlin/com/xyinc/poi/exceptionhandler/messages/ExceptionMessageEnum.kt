package com.xyinc.poi.exceptionhandler.messages

enum class ExceptionMessageEnum {
    POI_ALREADY_EXISTS("Point of Interest already exists"),
    POI_NAME_IS_BLANK("Point of Interest name is blank");

    val message : String

    private constructor(message: String) {
        this.message = message
    }

}