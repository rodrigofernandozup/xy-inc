package com.xyinc.poi.exceptionhandler

import com.xyinc.poi.exceptionhandler.exception.PoiAlreadyExistsException
import com.xyinc.poi.exceptionhandler.exception.PoiInvalidCoordinateException
import com.xyinc.poi.exceptionhandler.exception.PoiNameIsBlankException
import com.xyinc.poi.exceptionhandler.messages.ExceptionMessageEnum
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ExceptionControllerAdvice: ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [(PoiAlreadyExistsException::class)])
    fun handlePoiAlreadyExists(ex: PoiAlreadyExistsException, request: WebRequest): ResponseEntity<Error> {
        return ResponseEntity(Error(ExceptionMessageEnum.POI_ALREADY_EXISTS.message), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [(PoiNameIsBlankException::class)])
    fun handlePoiNameIsBlank(ex: PoiNameIsBlankException, request: WebRequest): ResponseEntity<Error> {
        return ResponseEntity(Error(ExceptionMessageEnum.POI_NAME_IS_BLANK.message), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [(PoiInvalidCoordinateException::class)])
    fun handlePoiNameIsBlank(ex: PoiInvalidCoordinateException, request: WebRequest): ResponseEntity<Error> {
        return ResponseEntity(Error(ExceptionMessageEnum.POI_INVALID_COORDINATE.message), HttpStatus.BAD_REQUEST)
    }
}