package com.xyinc.poi.service

import com.xyinc.poi.entity.PoiEntity
import com.xyinc.poi.exceptionhandler.exception.PoiAlreadyExistsException
import com.xyinc.poi.exceptionhandler.exception.PoiNameIsBlankException
import com.xyinc.poi.repository.PoiRepository
import org.springframework.stereotype.Service
import kotlin.math.abs
import kotlin.math.pow

@Service
class PoiService (private val poiRepository: PoiRepository) {

    fun getAllPois(): List<PoiEntity> =
        poiRepository.findAll().toList()

    fun createPoi(poiEntity: PoiEntity): PoiEntity? =
        if (validatePoi(poiEntity) && !poiRepository.findById(poiEntity.name).isPresent) {
            poiRepository.save(poiEntity)
        } else throw PoiAlreadyExistsException()

    fun getPoiByCoordinatesAndMaxDistance(xCoordinate: Long, yCoordinate: Long, maxDistance: Long): List<PoiEntity> =
        poiRepository.findAll().filter { poiEntity: PoiEntity ->
            isInsidePerimeterDistance(poiEntity, xCoordinate, yCoordinate, maxDistance) }

    private fun validatePoi(poiEntity: PoiEntity) : Boolean {
        if(poiEntity.name.isBlank()) {
            throw PoiNameIsBlankException();
        }
        return true
    }

    private fun isInsidePerimeterDistance(poiEntity: PoiEntity, xCoordinateReference: Long, yCoordinateReference: Long, maxDistance: Long): Boolean =
        (abs(poiEntity.xCoordinates) - abs(xCoordinateReference)).toDouble().pow(2) +
                ((abs(poiEntity.yCoordinates) - abs(yCoordinateReference)).toDouble()).pow(2) <= maxDistance.toDouble().pow(2)

}


